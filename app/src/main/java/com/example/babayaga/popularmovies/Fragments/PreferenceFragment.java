package com.example.babayaga.popularmovies.fragments;


import android.os.Bundle;

import com.example.babayaga.popularmovies.R;


public class PreferenceFragment extends android.preference.PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);


    }
}


