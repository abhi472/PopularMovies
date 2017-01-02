package com.example.babayaga.popularmovies.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.fragments.Fav_fargment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends AppCompatActivity {
    @BindView(R.id.activity_favorite)
    RelativeLayout frag_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);


        if (frag_container != null) {
            Fragment fragment = new Fav_fargment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.activity_favorite, fragment);

            transaction.commit();
        }

    }
}
