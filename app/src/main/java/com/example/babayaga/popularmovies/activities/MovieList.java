package com.example.babayaga.popularmovies.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.babayaga.popularmovies.callbacks.TwoPaneClickListener;
import com.example.babayaga.popularmovies.fragments.DetailFragment;
import com.example.babayaga.popularmovies.fragments.ErrorFragment;
import com.example.babayaga.popularmovies.fragments.MovieFragment;
import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.utils.Constants;
import com.facebook.stetho.Stetho;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieList extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener , TwoPaneClickListener{

    @BindView(R.id.fragment_container)
    RelativeLayout frag_container;
    @Nullable
    @BindView(R.id.detail_container)
    RelativeLayout mFragment;
    Boolean mTwoPane = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        Stetho.initializeWithDefaults(this);
        ButterKnife.bind(this);


        if(mFragment != null)
        {
            mTwoPane = true;
        }
        Log.d("tag", "onPost: "+mTwoPane);


        if(Constants.getInstance().isNetworkAvailable(this)) {
            MovieFragment fragment = new MovieFragment();

            if (frag_container != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, fragment);

                transaction.commit();
            }
        }
        else
        {       ErrorFragment fragment = new ErrorFragment();
            if (findViewById(R.id.fragment_container) != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, fragment);

                transaction.commit();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(MovieList.this, SettingsActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.settings2) {
            Intent intent = getIntent();
            startActivity(intent);
        }
        if (item.getItemId() == R.id.settings3) {
            Intent intent = new Intent(MovieList.this,FavoriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("start", "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("stop", "onStart: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("destroy", "onStart: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("pause", "onStart: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("resume", "onStart: ");

    }
}


