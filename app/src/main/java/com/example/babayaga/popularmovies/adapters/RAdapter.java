package com.example.babayaga.popularmovies.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

import com.example.babayaga.popularmovies.activities.DetailActivity;
import com.example.babayaga.popularmovies.callbacks.TwoPaneClickListener;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.utils.Constants;
import com.example.babayaga.popularmovies.utils.CustomImageView;
import com.example.babayaga.popularmovies.utils.DisplayUtils;
import com.example.babayaga.popularmovies.utils.FavoriteAdder;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mits on 7/9/16.
 */

public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {

    private ArrayList<MovieResults> arr = new ArrayList<>();
    private  Context con;
    private FavoriteAdder favoriteAdder;

    public RAdapter(ArrayList<MovieResults> arr, Context con)
    {
        this.arr=arr;
        this.con = con;

    }

    @Override
    public RAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards,parent,false);
        ViewHolder holder = new ViewHolder(root);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RAdapter.ViewHolder holder, int position) {

        favoriteAdder = FavoriteAdder.getinstance(con);

        Picasso.with(con)
                .load(Constants.getInstance().imageApi(arr.get(position).getPoster_path(),"320"))
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)         // optional
                .into(holder.img);
        holder.name.setText(arr.get(position).getTitle());


        //holder.cardView.setLayoutParams(params());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(con,DetailActivity.class);
                intent.putExtra("id",arr.get(holder.getAdapterPosition()).getId());
                intent.putExtra("name",arr.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("release",arr.get(holder.getAdapterPosition()).getRelease_date());
                intent.putExtra("vote",arr.get(holder.getAdapterPosition()).getVote_average());
                intent.putExtra("synopsis",arr.get(holder.getAdapterPosition()).getOverview());
                intent.putExtra("poster",arr.get(holder.getAdapterPosition()).getPoster_path());
                intent.putExtra("back",arr.get(holder.getAdapterPosition()).getBack_path());
                ((TwoPaneClickListener)con).OnCardclick(intent);
            }
        });

        if(!favoriteAdder.isFav(arr.get(position)))
        {
            holder.toggle.setChecked(true);
        }

        holder.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favoriteAdder.isFav(arr.get(holder.getAdapterPosition()))) {
                     favoriteAdder.addFav(arr.get(holder.getAdapterPosition()));

                }
                else {
                    favoriteAdder.remFav(arr.get(holder.getAdapterPosition()));
                }

            }
        });



    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        CustomImageView img;
        @BindView(R.id.card)
        CardView cardView;
        @BindView(R.id.fav)
        ToggleButton toggle;
        @BindView(R.id.name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ButterKnife.setDebug(true);

        }
    }

}
