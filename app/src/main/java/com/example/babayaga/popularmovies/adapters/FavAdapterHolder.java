package com.example.babayaga.popularmovies.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.babayaga.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abhi on 12/16/16.
 */
public  class FavAdapterHolder extends RecyclerView.ViewHolder{


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.card)
    CardView cardView;
    @BindView(R.id.fav)
    ToggleButton toggle;
    @BindView(R.id.name)
    TextView name;

    public  FavAdapterHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
        ButterKnife.setDebug(true);

    }


}
