package com.example.babayaga.popularmovies.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.babayaga.popularmovies.Fragments.PreferenceFragment;
import com.example.babayaga.popularmovies.R;

/**
 * Created by mits on 7/10/16.
 */

public class SettingsActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        getSupportActionBar();
        PreferenceFragment fragment = new PreferenceFragment();
        FragmentManager fragmentmanager = getFragmentManager();
        FragmentTransaction transaction = fragmentmanager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }

}

