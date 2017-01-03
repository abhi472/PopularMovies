package com.example.babayaga.popularmovies.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Movie;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.babayaga.popularmovies.R;
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
    private MovieResults results;
    private FavoriteAdder favoriteAdder;


    public FavAdapter(Cursor cursor, Context context) {
        super(cursor);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(FavAdapterHolder viewHolder, Cursor cursor, final int position) {


        favoriteAdder = FavoriteAdder.getinstance(context);
        final float width = DisplayUtils.getInstance(context).returnWidth();
        final float height = 1.5f * width / 2;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) width / 2, (int) height);
        params.setMargins(5, 5, 5, 5);
        holder.cardView.setLayoutParams(params);
        if (cursor != null) {
            results = MovieList.fromCursor(cursor);
            holder.name.setText(results.getTitle());
            Picasso.with(context)
                    .load(Constants.getInstance().imageApi(results.getPoster_path(), "320"))
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)         // optional
                    .into(holder.img);
        }
        holder.toggle.setChecked(true);
        holder.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteAdder.remFav(getItem(position));
                //Log.d("TAG", "onClick: "+FavAdapter.super.getItemCount()+" "+ holder.getAdapterPosition()+" "+position);

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
