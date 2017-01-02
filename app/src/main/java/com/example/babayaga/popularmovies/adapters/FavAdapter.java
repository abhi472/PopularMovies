package com.example.babayaga.popularmovies.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.models.MovieList;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.utils.Constants;
import com.example.babayaga.popularmovies.utils.DisplayUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by abhi on 12/16/16.
 */

public class FavAdapter extends CursorRecyclerViewAdapter<FavAdapterHolder> {
    private Context context;
    private FavAdapterHolder holder;
    private MovieResults results;


    public FavAdapter(Cursor cursor, Context context) {
        super(cursor);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(FavAdapterHolder viewHolder, Cursor cursor) {


        final float width = DisplayUtils.getInstance(context).returnWidth();
        final float height = 1.5f * width / 2;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) width / 2, (int) height);
        params.setMargins(5, 5, 5, 5);
        holder.cardView.setLayoutParams(params);
        if (cursor != null) {
            results = MovieList.fromCursor(cursor);
            holder.name.setText(results.getTitle());
            Picasso.with(context)
                    .load(Constants.getInstance().imageApi(results.getPoster_path(),"320"))
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)         // optional
                    .into(holder.img);
        }
    }

    @Override
    public FavAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cards, parent, false);
        holder = new FavAdapterHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
