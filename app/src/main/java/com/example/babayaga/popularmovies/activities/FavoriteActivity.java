package com.example.babayaga.popularmovies.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.callbacks.ToggleClickFav;
import com.example.babayaga.popularmovies.callbacks.TwoPaneClickListener;
import com.example.babayaga.popularmovies.fragments.DetailFragment;
import com.example.babayaga.popularmovies.fragments.Fav_fargment;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.utils.FavoriteAdder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends AppCompatActivity implements ToggleClickFav , TwoPaneClickListener {
    @BindView(R.id.fragment_container)
    RelativeLayout frag_container;
    @Nullable
    @BindView(R.id.detail_container)
    RelativeLayout mFragment;
    FragmentTransaction transaction;
    Fragment fragment = null;
    Boolean mTwoPane = false;
    private FavoriteAdder favoriteAdder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        favoriteAdder = new FavoriteAdder(this);

        if(mFragment != null)
        {
            mTwoPane = true;
        }


        if (frag_container != null) {
             fragment = new Fav_fargment();

            transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, fragment);

            transaction.commit();
        }

    }
    @Override
    public void onToggleClick(MovieResults results) {
        finish();
        overridePendingTransition( 0, 0);
        favoriteAdder.remFav(results);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);


    }


    @Override
    public void OnCardclick(Intent intent) {
        if(mTwoPane)
        {
            DetailFragment detailFragment = new DetailFragment();
            mFragment.setVisibility(View.VISIBLE);
            Bundle bundle = intent.getExtras();
            detailFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.detail_container, detailFragment);

            transaction.commit();

        }
        else
        {
            startActivity(intent);
        }
    }
}
