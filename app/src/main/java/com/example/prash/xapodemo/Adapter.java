package com.example.prash.xapodemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prash.xapodemo.pojo.SearchRepo;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    public static String TAG = "CityAdapter";
    private Context context;

    private ArrayList<SearchRepo> searchRepos = new ArrayList<SearchRepo>();
    private Adapter.AdapterCallback callback;

    public Adapter(ArrayList<SearchRepo> searchRepos, Context context, Adapter.AdapterCallback callback){
        this.context = context;
        this.callback = callback;
        this.searchRepos = searchRepos;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.listitem_layout, parent, false);
        Adapter.ViewHolder viewHolder = new Adapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final Adapter.ViewHolder holder, final int position) {

        SearchRepo pojo =  searchRepos.get(position);

        holder.textViewName.setText(pojo.getName());
        holder.language.setText(pojo.getLanguage());
    }

    @Override
    public int getItemCount() {
        return searchRepos.size();
    }

    interface AdapterCallback {
        void onClick(SearchRepo searchRepo);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewName;
        public TextView language;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewName = (TextView) itemView.findViewById(R.id.name);
            language = (TextView) itemView.findViewById(R.id.lang);
        }

        @Override
        public void onClick(View v) {
            if(NO_POSITION != getAdapterPosition()){
                callback.onClick(searchRepos.get(getAdapterPosition()));
            }
        }
    }
}

