package com.example.babayaga.popularmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.BinderThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.text.setText(reviewResults.get(position).getContent().trim());
        holder.author.setText(reviewResults.get(position).getAuthor());
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/review/"+reviewResults.get(holder.getAdapterPosition()).getId()));
                if (browserIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(browserIntent);
                }
                else{
                    Toast.makeText(context,"no apps available",Toast.LENGTH_SHORT).show();
                }
            }
        });

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
