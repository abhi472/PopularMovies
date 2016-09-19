package com.example.babayaga.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.BinderThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.models.Results;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        ViewHolder holder = new ViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.text.setText(reviewResults.get(position).getContent());
        Log.d("reviewadapter", "onBindViewHolder: "+reviewResults.get(position).getContent());
        holder.author.setText(reviewResults.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return reviewResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.reviewText)
        TextView text;
        @BindView(R.id.author)
        TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ButterKnife.setDebug(true);
        }
    }

}
