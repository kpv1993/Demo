package com.example.prash.xapodemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.prash.xapodemo.pojo.SearchRepo;
import com.example.prash.xapodemo.viewmodel.MainViewModel;
import com.example.prash.xapodemo.viewmodel.ViewModelFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String LIST = "list";
    MainViewModel viewModel;
    ArrayList<SearchRepo> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    Adapter adapter;
    int totalItemCount=0, lastVisibleItem = 0, visibleThreshold = 10;
    private boolean loading = false;
    int pageNum = 1;

    @BindView(R.id.rView)
    RecyclerView rView;
    @BindView(R.id.pBar)
    ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.trending_repos_text);

        //Standard lines for architecture components
        ViewModelFactory factory = ViewModelFactory.getInstance();
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        getLifecycle().addObserver(viewModel);

        adapter = new Adapter(list,MainActivity.this, cityCallback);
        layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);

        rView.addOnScrollListener(scrollListener);

        viewModel.getSuccessObservable().observe(this, new Observer<ArrayList<SearchRepo>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SearchRepo> searchRepos) {
                Log.d(TAG, searchRepos.toString());
                loading = false;
                pBar.setVisibility(View.GONE);
                list.addAll(searchRepos);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getFailureObservable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                pBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });

        //thread switching happens inside this method in viewModel
        viewModel.getGithubTrendingList(pageNum);
        pBar.setVisibility(View.VISIBLE);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            totalItemCount = layoutManager.getItemCount();
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                pageNum++;
                viewModel.getGithubTrendingList(pageNum);
                loading = true;
            }
        }
    };

    Adapter.AdapterCallback cityCallback = new Adapter.AdapterCallback() {
        @Override
        public void onClick(SearchRepo searchRepo) {
            Intent i = new Intent(MainActivity.this, DetailedViewActivity.class);
            i.putExtra(LIST,searchRepo);
            startActivity(i);
        }
    };
}
