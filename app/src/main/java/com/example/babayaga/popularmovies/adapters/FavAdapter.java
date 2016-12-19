package com.example.babayaga.popularmovies.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.utils.DisplayUtils;

/**
 * Created by abhi on 12/16/16.
 */

public class FavAdapter extends CursorRecyclerViewAdapter<FavAdapterHolder> {
    private Context context;
    private FavAdapterHolder holder;



    public FavAdapter(Cursor cursor, Context context) {
        super(cursor);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(FavAdapterHolder viewHolder, Cursor cursor) {


        final float width = DisplayUtils.getInstance(context).returnWidth();
        final float height = 1.5f*width/2;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)width/2,(int) height);
        params.setMargins(5,5,5,5);
        holder.cardView.setLayoutParams(params);
    }

    @Override
    public FavAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cards,parent,false);
        holder  = new FavAdapterHolder(view);
        return  holder;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
