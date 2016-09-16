package com.example.babayaga.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.models.Results;

import java.util.ArrayList;

/**
 * Created by abhishek on 16/9/16.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    ArrayList<Results> reviewResults = new ArrayList<>();
    Context context;

    public ReviewAdapter(Context context,ArrayList<Results> reviewResults)
    {
        this.context = context;
        this.reviewResults = reviewResults;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewlayout,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return reviewResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
