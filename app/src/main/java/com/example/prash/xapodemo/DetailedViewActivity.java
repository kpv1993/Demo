package com.example.prash.xapodemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.prash.xapodemo.pojo.SearchRepo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedViewActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.language)
    TextView language;
    @BindView(R.id.url)
    TextView url;
    @BindView(R.id.watchersCount)
    TextView watchersCount;

    SearchRepo searchRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);
        ButterKnife.bind(this);

        searchRepo = getIntent().getParcelableExtra(MainActivity.LIST);

        ActionBar actionBar = getSupportActionBar();
        if(searchRepo.getName() != null || searchRepo.getName().trim().length() != 0){
            actionBar.setTitle(searchRepo.getName());
            name.setText(searchRepo.getName());
        } else {
            actionBar.setTitle(R.string.trending_repos_text);
            name.setText(getString(R.string.no_name_found));
        }

        if(searchRepo.getUrl() != null) {
            url.setText(searchRepo.getUrl());
        }

        if(searchRepo.getDescription() != null && searchRepo.getDescription().trim().length() != 0){
            desc.setText(searchRepo.getDescription());
        } else {
            desc.setText(getString(R.string.no_desc_found));
        }

        if(searchRepo.getLanguage() != null) {
            language.setText(searchRepo.getLanguage());
        }

        watchersCount.setText(String.valueOf(searchRepo.getWatchersCount()));

    }
}
