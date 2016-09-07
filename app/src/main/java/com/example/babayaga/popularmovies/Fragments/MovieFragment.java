package com.example.babayaga.popularmovies.Fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babayaga.popularmovies.Parser.JsonPArser;
import com.example.babayaga.popularmovies.Models.MovieD;
import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.Adapters.RAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener , LoaderManager.LoaderCallbacks<Cursor> {

    private ProgressDialog dialog;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private String url = "";
    private Boolean sortOrder = false;
    private SharedPreferences preference;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        url = "https://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=7a83a78b4e9a7bdbd184461cedb61494";

        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);
        preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));//context and spansizes as the attributes
        preference.registerOnSharedPreferenceChangeListener(this);


        String s = preference.getString("pref_syncConnectionType", "1");
        sortOrder = true;
        update(s, sortOrder);
        sortOrder = false;


        return root;
    }


    public void update(String s, Boolean check) {


        if (check) {
            if (s.equalsIgnoreCase("1")) {
                url = "http://api.themoviedb.org/3/movie/top_rated?api_key=7a83a78b4e9a7bdbd184461cedb61494";
            } else if (s.equalsIgnoreCase("2")) {
                url = "https://api.themoviedb.org/3/movie/popular?api_key=7a83a78b4e9a7bdbd184461cedb61494";

            }

            tasks task = new tasks();
            task.execute(url);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        String s = preference.getString("pref_syncConnectionType", "1");
        update(s, sortOrder);
        sortOrder = false;

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        sortOrder = true;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    class tasks extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getContext());
            dialog.setMessage(getString(R.string.loading));
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        StringBuilder result = new StringBuilder();

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            JsonPArser jp = new JsonPArser();
            ArrayList<MovieD> arr = jp.setData(s);
            RAdapter rAdapter = new RAdapter(arr, getContext());
            recyclerView.setAdapter(rAdapter);
        }


    }
}