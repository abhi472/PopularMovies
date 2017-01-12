package com.example.babayaga.popularmovies.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.callbacks.ToggleClickFav;
import com.example.babayaga.popularmovies.callbacks.TwoPaneClickListener;
import com.example.babayaga.popularmovies.fragments.DetailFragment;
import com.example.babayaga.popularmovies.fragments.ErrorFragment;
import com.example.babayaga.popularmovies.fragments.Fav_fargment;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.utils.FavoriteAdder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends AppCompatActivity implements ToggleClickFav, TwoPaneClickListener {
    @BindView(R.id.fragment_container)
    RelativeLayout frag_container;
    @Nullable
    @BindView(R.id.detail_container)
    RelativeLayout mFragment;
    FragmentTransaction transaction;
    Fragment fragment = null;
    Boolean mTwoPane = false;
    @BindView(R.id.toolbar)
    ViewGroup group;
    Toolbar toolbar;
    private FavoriteAdder favoriteAdder;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        toolbar = (Toolbar) group.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_action_bar);
        setTitle("Favorite Movies");
        favoriteAdder = new FavoriteAdder(this);
        if (mFragment != null) {
            mTwoPane = true;
        }
        bundle = getIntent().getExtras();
        if (bundle != null)
            if (bundle.getInt("cursor") == 0) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "No Favorites Added");
                ErrorFragment fragment = new ErrorFragment();
                fragment.setArguments(bundle);
                if (frag_container != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, fragment);

                    transaction.commit();
                }

            } else {
                Fav_fargment fragment = new Fav_fargment();

                if (frag_container != null) {


                    transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, fragment);

                    transaction.commit();
                }
            }

        else {
            Fav_fargment fragment = new Fav_fargment();


            if (frag_container != null) {


                transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, fragment);

                transaction.commit();
            }
        }

    }

    @Override
    public void onToggleClick(MovieResults results, int cursor) {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        favoriteAdder.remFav(results);

        if (cursor-1 == 0) {
            Intent intent = getIntent();
            intent.putExtra("cursor", 0);
            startActivity(intent);
        } else
        overridePendingTransition(0, 0);


    }

    @Override
    protected void onStart() {
        super.onStart();
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public void OnCardclick(Intent intent) {
        if (mTwoPane) {
            DetailFragment detailFragment = new DetailFragment();
            mFragment.setVisibility(View.VISIBLE);
            Bundle bundle = intent.getExtras();
            detailFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.detail_container, detailFragment);

            transaction.commit();

        } else {
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.explore) {
            Intent intent = new Intent(FavoriteActivity.this, MovieList.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
