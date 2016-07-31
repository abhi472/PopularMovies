package com.example.babayaga.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

//        app = (AppBarLayout)findViewById(R.id.app_bar_layout);
        app.addOnOffsetChangedListener(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        scrim = (ImageView) findViewById(R.id.scrim);
//        thumb = (ImageView) findViewById(R.id.thumb);
//        back = (ImageView) findViewById(R.id.image);
//        title = (TextView) findViewById(R.id.title_movie);
//        release = (TextView) findViewById(R.id.release);
//        ratings = (TextView) findViewById(R.id.ratings);
//        overview = (TextView) findViewById(R.id.synopsis);
//        pthumb = (ProgressBar) findViewById(R.id.progressBar2);
//        pback = (ProgressBar) findViewById(R.id.progressBar);

        setSupportActionBar(toolbar);
        setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String s1 = "http://image.tmdb.org/t/p/w500/";
        Picasso.with(this)
                    .load(s1 + bundle.getString("back"))
                    .placeholder(R.drawable.error) // optional
                    .error(R.drawable.error)         // optional
                    .into(back, new Callback() {
                        @Override
                        public void onSuccess() {
                            pback.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            pback.setVisibility(View.GONE);

                        }
                    });


        String s2 = "http://image.tmdb.org/t/p/w185/";
        Picasso.with(this)
                .load(s2 +bundle.getString("poster"))
                .placeholder(R.drawable.no_image ) // optional
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




    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        //measuring for alpha
        int toolBarHeight = toolbar.getMeasuredHeight();
        int appBarHeight = appBarLayout.getMeasuredHeight();
        float scroll = ((float) appBarHeight - toolBarHeight) + verticalOffset;
        Float f = ( scroll/ ( (float) appBarHeight - toolBarHeight)) * 255;
        scrim.getBackground().setAlpha(255 - Math.round(f));
        Log.d("offset", "onOffsetChanged: "+f+"");
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
}
