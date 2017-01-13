package com.example.babayaga.popularmovies.fragments;


import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.activities.DetailActivity;
import com.example.babayaga.popularmovies.adapters.ReviewAdapter;
import com.example.babayaga.popularmovies.adapters.TrailerAdapter;
import com.example.babayaga.popularmovies.apis.ApiManager;
import com.example.babayaga.popularmovies.apis.ModelManagerNew;
import com.example.babayaga.popularmovies.callbacks.ICallBack;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.models.Results;
import com.example.babayaga.popularmovies.models.TrailerResults;
import com.example.babayaga.popularmovies.utils.Constants;
import com.example.babayaga.popularmovies.utils.DisplayUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener, ICallBack {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.rlv)
    RelativeLayout rlv;
    @Nullable
    @BindView(R.id.card)
    CardView rlv2;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar;
    @BindView(R.id.reviews)
    RecyclerView reviewList;
    @Nullable
    @BindView(R.id.play)
    ImageView play;
    @Nullable
    @BindView(R.id.scrim)
    ImageView scrim;
    @BindView(R.id.thumb)
    ImageView thumb;
    @Nullable
    @BindView(R.id.image)
    ImageView back;
    @Nullable
    @BindView(R.id.app_bar_layout)
    AppBarLayout app;
    @BindView(R.id.title_movie)
    TextView title;
    @BindView(R.id.release)
    TextView release;
    @BindView(R.id.ratings)
    TextView ratings;
    @BindView(R.id.synopsis)
    TextView overview;
    @Nullable
    @BindView(R.id.progressBar)
    ProgressBar pback;
    @BindView(R.id.progressBar4)
    ProgressBar pBar;
    @BindView(R.id.trailers)
    RecyclerView trailers;
    @BindView(R.id.progressBar2)
    ProgressBar pthumb;
    private String name;
    Bundle bundle;
    MovieResults results;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_detail, container, false);
        setHasOptionsMenu(true);

        ButterKnife.bind(this, root);

        bundle = getArguments();

        if (toolbar != null) {

            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            (getActivity()).setTitle(null);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        results = bundle.getParcelable("movie");


        name = results.getTitle();
        overview.setText(results.getOverview());
        ratings.setText(results.getVoteAverage().toString());
        release.setText(results.getReleaseDate());
        title.setText(name);


        if (app != null)
            onePaneConfig(root);
        else
            twoPaneConfig(root);
        return root;

    }

    private void onePaneConfig(View view) {
        app.addOnOffsetChangedListener(this);


        if (Constants.getInstance().isNetworkAvailable(getContext())) {

            ApiManager.newInstance(this).requestGet(Constants.getInstance().reviewApi(results.getId().toString()),1);
            ApiManager.newInstance(this).requestGet(Constants.getInstance().trailerApi(results.getId().toString()),2);
            Picasso.with(getContext())
                    .load(Constants.getInstance().imageApi(results.getBackdropPath(), "500"))
                    .placeholder(R.drawable.error) // optional
                    .error(R.drawable.error)         // optional
                    .into(back, new Callback() {
                        @Override
                        public void onSuccess() {

                            play.setVisibility(View.VISIBLE);
                            pback.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            pback.setVisibility(View.GONE);

                        }
                    });


            Picasso.with(getContext())
                    .load(Constants.getInstance().imageApi(results.getPosterPath(), "185"))
                    .placeholder(R.drawable.no_image) // optionals2 +
                    .error(R.drawable.no_image)         // optional
                    .into(thumb, new Callback() {
                        @Override
                        public void onSuccess() {
                            pthumb.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            pthumb.setVisibility(View.GONE);
                        }
                    });
        } else {
            pback.setVisibility(View.GONE);
            pthumb.setVisibility(View.GONE);
            pBar.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            Snackbar.make(view, "Additional Data requires Internet Connectivity", Snackbar.LENGTH_LONG).show();
        }

    }

    private void twoPaneConfig(View view) {
        if (Constants.getInstance().isNetworkAvailable(getContext())) {

            ApiManager.newInstance(this).requestGet(Constants.getInstance().reviewApi(results.getId().toString()), 1);
            ApiManager.newInstance(this).requestGet( Constants.getInstance().trailerApi(results.getId().toString()), 2);

            Picasso.with(getContext())
                    .load(Constants.getInstance().imageApi(results.getPosterPath(), "185"))
                    .placeholder(R.drawable.no_image) // optionals2 +
                    .error(R.drawable.no_image)         // optional
                    .into(thumb, new Callback() {
                        @Override
                        public void onSuccess() {
                            pthumb.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            pthumb.setVisibility(View.GONE);
                        }
                    });
        } else {
            pthumb.setVisibility(View.GONE);
            pBar.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            Snackbar.make(view, "Additional Data requires Internet Connectivity", Snackbar.LENGTH_LONG).show();
        }

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        //measuring for alpha
        float width;
        DisplayUtils displayUtils = DisplayUtils.getInstance(getContext());

        width = displayUtils.returnWidth();
        float px = 90 * displayUtils.dpToPixel();
        int toolBarHeight = toolbar.getMeasuredHeight();
        int appBarHeight = appBarLayout.getMeasuredHeight();
        float scroll = ((float) appBarHeight - toolBarHeight) + verticalOffset;

        Float f = (scroll / ((float) appBarHeight - toolBarHeight)) * 255;
        Float f2 = (scroll / (appBarHeight - toolBarHeight)) * px;
        scrim.getBackground().setAlpha(255 - Math.round(f));
        if (rlv2 == null) {
            RelativeLayout.LayoutParams layout = (RelativeLayout.LayoutParams) rlv.getLayoutParams();
            layout.setMargins(Math.round(10 * displayUtils.dpToPixel()), Math.round(f2 + (10 * displayUtils.dpToPixel())), 0, 0);


            rlv.setLayoutParams(layout);
        } else {
            CardView.LayoutParams layout = (CardView.LayoutParams) rlv2.getLayoutParams();
            layout.width = Math.round(2 * width / 3);
            layout.gravity = Gravity.CENTER_HORIZONTAL;

            rlv2.setLayoutParams(layout);

        }
    }

    @Override
    public void onRecieve(String str) {

    }

    @Override
    public void onRecieve(String str, int icallbackId) {

        if (icallbackId == 1) {
            progressBar.setVisibility(View.GONE);
            reviewList.setVisibility(View.VISIBLE);
            reviewList.setNestedScrollingEnabled(false);
            ArrayList<Results> arr = ModelManagerNew.getInstance().getReviews(str).getResults();
            ReviewAdapter rAdapter = new ReviewAdapter(getActivity(), arr);
            reviewList.setLayoutManager(new LinearLayoutManager(getActivity()));
            reviewList.setAdapter(rAdapter);

        } else if (icallbackId == 2) {
            trailers.setVisibility(View.VISIBLE);
            pBar.setVisibility(View.GONE);
            ArrayList<TrailerResults> arr = ModelManagerNew.getInstance().getTrailers(str).getResults();
            TrailerAdapter rAdapter = new TrailerAdapter(getActivity(), arr);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            trailers.setLayoutManager(layoutManager);

            trailers.setAdapter(rAdapter);
        }


    }

    @Override
    public void onError(String str) {

    }

    @Override
    public void onError(String str, int id) {

    }


//    class Detailtasks extends AsyncTask<String, String, String> {
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
//            progressBar.setVisibility(View.GONE);
//            reviewList.setVisibility(View.VISIBLE);
//            reviewList.setNestedScrollingEnabled(false);
//            JsonPArser jp = new JsonPArser();
//            ArrayList<Results> arr = jp.setReviewData(s).getResults();
//            ReviewAdapter rAdapter = new ReviewAdapter(getActivity(), arr);
//            reviewList.setLayoutManager(new LinearLayoutManager(getActivity()));
//            reviewList.setAdapter(rAdapter);
//        }
//
//
//    }
//
//    class TrailerTask extends AsyncTask<String, String, String> {
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
//            trailers.setVisibility(View.VISIBLE);
//            pBar.setVisibility(View.GONE);
//            JsonPArser jp = new JsonPArser();
//            ArrayList<TrailerResults> arr = jp.setTrailerData(s).getResults();
//            TrailerAdapter rAdapter = new TrailerAdapter(getActivity(), arr);
//
//            LinearLayoutManager layoutManager
//                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//            trailers.setLayoutManager(layoutManager);
//
//            trailers.setAdapter(rAdapter);
//        }
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (app != null) {
            inflater.inflate(R.menu.detail_menu, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (app != null) {

            if (item.getItemId() == R.id.settings) {
                String url = "https://www.google.co.in/#q=" + name + " movie";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                if (i.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(i);
                }
                else{
                    Toast.makeText(getContext(), "no apps available", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);

    }


}
