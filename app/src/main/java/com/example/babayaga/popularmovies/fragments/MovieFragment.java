package com.example.babayaga.popularmovies.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class MovieFragment extends Fragment implements ICallBack{

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.bar)
    ProgressBar bar;
    private int grid = 2;
    private String url = "";
    private Boolean sortOrder = false;
    private SharedPreferences preference;
    private static final int MOVIES_LOADER = 0;
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
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);
        preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(recyclerView.getTag().equals("simple_land"))
        {
            grid = 3;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),grid));//context and spansizes as the attributes

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

//            tasks task = new tasks();
//            task.execute(url);
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


//    class tasks extends AsyncTask<String, String, String> {
//        @Override
//        protected void onPreExecute() {
//        }
//
//        StringBuilder result = new StringBuilder();
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                URL url = new URL(params[0]);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    result.append(line);
//                }
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return result.toString();
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            bar.setVisibility(View.GONE);
//            recyclerView.setVisibility(View.VISIBLE);
//            JsonPArser jp = new JsonPArser();
//            ArrayList<MovieResults> arr = jp.setData(s).getResults();
//            Log.d("tag", "onPostExecute: "+grid);
//            RAdapter rAdapter = new RAdapter(arr, getContext());
//            recyclerView.setAdapter(rAdapter);
//        }
//
//
//    }
}
