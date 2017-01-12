package com.example.babayaga.popularmovies.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.adapters.RAdapter;
import com.example.babayaga.popularmovies.adapters.ReviewAdapter;
import com.example.babayaga.popularmovies.fragments.DetailFragment;
import com.example.babayaga.popularmovies.fragments.ErrorFragment;
import com.example.babayaga.popularmovies.fragments.MovieFragment;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.models.Results;
import com.example.babayaga.popularmovies.parser.JsonPArser;
import com.example.babayaga.popularmovies.utils.Constants;
import com.example.babayaga.popularmovies.utils.DisplayUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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

public class DetailActivity extends AppCompatActivity {

    DetailFragment fragment;
    int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(Constants.getInstance().isNetworkAvailable(this)) {
            fragment = new DetailFragment();
            fragment.setArguments(bundle);

            if (findViewById(R.id.fragment_container) != null) {
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


}
