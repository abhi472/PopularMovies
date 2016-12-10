package com.example.babayaga.popularmovies.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
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
import com.example.babayaga.popularmovies.adapters.RAdapter;
import com.example.babayaga.popularmovies.adapters.ReviewAdapter;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.models.Results;
import com.example.babayaga.popularmovies.parser.JsonPArser;
import com.example.babayaga.popularmovies.utils.Constants;
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

public class DetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rlv)
    RelativeLayout rlv;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar;

    @BindView(R.id.reviews)
    RecyclerView reviewList;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.scrim)
    ImageView scrim;
    @BindView(R.id.thumb)
    ImageView thumb;
    @BindView(R.id.image)
    ImageView back;
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
    @BindView(R.id.progressBar)
    ProgressBar pback;
    @BindView(R.id.progressBar2)
    ProgressBar pthumb;
    private String name;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        app.addOnOffsetChangedListener(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        setSupportActionBar(toolbar);
        setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Detailtasks dTask = new Detailtasks();
        Log.d("id", "onCreate: " + bundle.getString("id"));
        dTask.execute(Constants.getInstance().reviewApi(bundle.getString("id")));


        Picasso.with(this)
                .load(Constants.getInstance().imageApi(bundle.getString("back"), "500"))
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


        Picasso.with(this)
                .load(Constants.getInstance().imageApi(bundle.getString("poster"), "185"))
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

        name = bundle.getString("name");

        overview.setText(bundle.getString("synopsis"));
        ratings.setText(bundle.getString("vote"));
        release.setText(bundle.getString("release"));
        title.setText(name);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "this", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        //measuring for alpha

        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float d = ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        float px = 54 * d;
        int imageWidth = thumb.getMeasuredWidth();
        int toolBarHeight = toolbar.getMeasuredHeight();
        int appBarHeight = appBarLayout.getMeasuredHeight();
        float scroll = ((float) appBarHeight - toolBarHeight) + verticalOffset;

        Float f = (scroll / ((float) appBarHeight - toolBarHeight)) * 255;
        Float f2 = (scroll / (appBarHeight - toolBarHeight)) * px;
        scrim.getBackground().setAlpha(255 - Math.round(f));
        RelativeLayout.LayoutParams layout = (RelativeLayout.LayoutParams) rlv.getLayoutParams();
        layout.setMargins(Math.round(10*d),Math.round(f2+(10*d)),0,0);


        rlv.setLayoutParams(layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.settings) {
            String url = "https://www.google.co.in/#q=" + name + " movie";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();


    }

    class Detailtasks extends AsyncTask<String, String, String> {

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
            progressBar.setVisibility(View.GONE);
            reviewList.setVisibility(View.VISIBLE);
            JsonPArser jp = new JsonPArser();
            ArrayList<Results> arr = jp.setReviewData(s).getResults();
            ReviewAdapter rAdapter = new ReviewAdapter(getApplicationContext(), arr);
//            LinearLayoutManager lmanager = new LinearLayoutManager(getApplicationContext()){
//
//                    @Override
//                    public boolean canScrollVertically() {
//                        return false;
//                    }
//                };
//            reviewList.setLayoutManager(lmanager);
            reviewList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            reviewList.setAdapter(rAdapter);
        }


    }
}
