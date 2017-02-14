package com.example.babayaga.popularmovies.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.babayaga.popularmovies.apis.ApiManager;
import com.example.babayaga.popularmovies.apis.ModelManagerNew;
import com.example.babayaga.popularmovies.callbacks.ICallBack;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.adapters.RAdapter;
import com.example.babayaga.popularmovies.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment implements ICallBack {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.bar)
    ProgressBar bar;
    private int grid = 2;
    private String url = "";
    private Boolean sortOrder = false;
    private SharedPreferences preference;
    private static final int MOVIES_LOADER = 0;
    RecyclerView.LayoutManager layoutManager ;

    // For the forecast view we're showing only a small subset of the stored data.
    // Specify the columns we need.
    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        url = Constants.getInstance().ratedMoviesApi();

        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, root);
        ButterKnife.setDebug(true);
        preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (recyclerView.getTag().equals("simple_land")) {
            grid = 3;
        }
        layoutManager = new GridLayoutManager(getContext(), grid);
        recyclerView.setLayoutManager(layoutManager);//context and spansizes as the attributes

        update(preference.getString("pref_syncConnectionType", "1"));

        return root;
    }




    public void update(String s) {


        if (s.equalsIgnoreCase("1")) {
            url = Constants.getInstance().ratedMoviesApi();
        } else if (s.equalsIgnoreCase("2")) {
            Log.d("tag", "update: 2");
            url = Constants.getInstance().popularMoviesApi();

        }

        ApiManager.newInstance(this).requestGet(url);
    }

    @Override
    public void onRecieve(String str) {
        bar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        ArrayList<MovieResults> arr = ModelManagerNew.getInstance().getAllMovies(str).getResults();
        RAdapter rAdapter = new RAdapter(arr, getContext());
        recyclerView.setAdapter(rAdapter);


    }





    @Override
    public void onRecieve(String str, int icallbackId) {

    }

    @Override
    public void onError(String str) {

    }

    @Override
    public void onError(String str, int id) {

    }

}
