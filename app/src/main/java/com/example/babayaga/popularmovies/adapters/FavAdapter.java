package com.example.babayaga.popularmovies.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Movie;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.activities.DetailActivity;
import com.example.babayaga.popularmovies.activities.FavoriteActivity;
import com.example.babayaga.popularmovies.callbacks.ToggleClickFav;
import com.example.babayaga.popularmovies.callbacks.TwoPaneClickListener;
import com.example.babayaga.popularmovies.models.MovieList;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.utils.Constants;
import com.example.babayaga.popularmovies.utils.DisplayUtils;
import com.example.babayaga.popularmovies.utils.FavoriteAdder;
import com.squareup.picasso.Picasso;

/**
 * Created by abhi on 12/16/16.
 */

public class FavAdapter extends CursorRecyclerViewAdapter<FavAdapterHolder> {
    private Context context;
    private FavAdapterHolder holder;
    private ToggleClickFav toggleClickFav;



    public FavAdapter(Cursor cursor, Context context) {
        super(cursor);
        this.context = context;
        toggleClickFav = (ToggleClickFav) context;


    }

    @Override
    public void onBindViewHolder(FavAdapterHolder viewHolder, final Cursor cursor, int position) {



        if (cursor != null) {
            MovieResults results = MovieList.fromCursor(cursor);
            holder.name.setText(results.getTitle());
            Picasso.with(context)
                    .load(Constants.getInstance().imageApi(results.getPosterPath(), "320"))
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)         // optional
                    .into(holder.img);
        }


        final int pos = position;
        View view = holder.view;

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("movie",getItem(pos));
                ((TwoPaneClickListener)context).OnCardclick(intent);
            }
        });
        holder.toggle.setChecked(true);
        holder.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleClickFav.onToggleClick(getItem(pos),getItemCount());


            }
        });
    }

    @Override
    public FavAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cards, parent, false);
        holder = new FavAdapterHolder(view);
        return holder;
    }
    @Nullable
    public MovieResults getItem(int position) {
        Cursor cursor = getCursor();
        if (cursor == null) {
            return null;
        }
        if (position < 0 || position > cursor.getCount()) {
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < position; i++) {
            cursor.moveToNext();
        }
        return MovieList.fromCursor(cursor);
    }

}
