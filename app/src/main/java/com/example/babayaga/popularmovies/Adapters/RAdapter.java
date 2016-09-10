package com.example.babayaga.popularmovies.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.babayaga.popularmovies.Activities.DetailActivity;
import com.example.babayaga.popularmovies.Models.MovieD;
import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.utils.FavoriteAdder;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mits on 7/9/16.
 */

public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {

    private ArrayList<MovieD> arr = new ArrayList<>();
    private static Context con;
    int i=0;
    FavoriteAdder favoriteAdder;
    ContentValues values;
    private static String s="http://image.tmdb.org/t/p/w320/";
    public RAdapter(ArrayList<MovieD> arr,Context con)
    {
        this.arr=arr;
        this.con = con;
        values = new ContentValues();

    }

    @Override
    public RAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards,parent,false);
        ViewHolder holder = new ViewHolder(root);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RAdapter.ViewHolder holder, final int position) {

        favoriteAdder = FavoriteAdder.getinstance(con);

        Picasso.with(con)
                .load(s+arr.get(position).poster_path)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)         // optional
                .into(holder.img);
        Log.d("TAG", "onBindViewHolder: " +position);
        holder.name.setText(arr.get(position).title);

        WindowManager wm = (WindowManager) con.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        final float width = metrics.widthPixels;
        final float height = 1.5f*width/2;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)width/2,(int) height);
        params.setMargins(5,5,5,5);
        holder.cardView.setLayoutParams(params);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(con,DetailActivity.class);
                intent.putExtra("name",arr.get(position).title);
                intent.putExtra("release",arr.get(position).release_date);
                intent.putExtra("vote",arr.get(position).vote_average);
                intent.putExtra("synopsis",arr.get(position).overview);
                intent.putExtra("poster",arr.get(position).poster_path);
                intent.putExtra("back",arr.get(position).back_path);
                con.startActivity(intent);
            }
        });

        holder.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(favoriteAdder.isFav(arr.get(position))) {
                     favoriteAdder.addFav(arr.get(position));
//                }
//                else {
//                    favoriteAdder.remFav(arr.get(position));
//                }
//
            }
        });



    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img) ImageView img;
        @BindView(R.id.card) CardView cardView;
        @BindView(R.id.fav) ToggleButton toggle;
        @BindView(R.id.name) TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ButterKnife.setDebug(true);

        }
    }
}
