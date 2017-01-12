package com.example.babayaga.popularmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.models.TrailerResults;
import com.example.babayaga.popularmovies.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abhi on 1/12/17.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private ArrayList<TrailerResults> trailerResultses = new ArrayList<>();
    private Context context;

    public TrailerAdapter(Context context,ArrayList<TrailerResults> reviewResults)
    {
        this.context = context;
        this.trailerResultses = reviewResults;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailerlayout,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Picasso.with(context)
                .load(Constants.getInstance().thumbNail(trailerResultses.get(position).getKey()))
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)         // optional
                .into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+ trailerResultses.get(holder.getAdapterPosition()).getKey()));
                context.startActivity(browserIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trailerResultses.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ButterKnife.setDebug(true);
        }
    }

}
