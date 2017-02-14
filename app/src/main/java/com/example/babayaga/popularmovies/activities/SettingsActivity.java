package com.example.babayaga.popularmovies.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;

import com.example.babayaga.popularmovies.fragments.PreferenceFragment;
import com.example.babayaga.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mits on 7/10/16.
 */

public class SettingsActivity extends AppCompatActivity{

    @BindView(R.id.toolbar)
    ViewGroup group;
    Toolbar toolbar;
    Fragment fragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        getSupportActionBar();
        ButterKnife.bind(this);
        toolbar = (Toolbar) group.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_action_bar);
        setTitle("Settings");
        if(savedInstanceState == null) {
            fragment = new PreferenceFragment();

        }
        else {
                fragment = getFragmentManager().getFragment(savedInstanceState, "mContent");
        }
        FragmentManager fragmentmanager = getFragmentManager();
        FragmentTransaction transaction = fragmentmanager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
        fragmentmanager.popBackStack();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, "mContent", fragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
        toolbar.setTitleTextColor(Color.WHITE);
    }


}

