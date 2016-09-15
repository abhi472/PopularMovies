package com.example.babayaga.popularmovies.parser;

import com.example.babayaga.popularmovies.models.MovieList;
import com.example.babayaga.popularmovies.models.MovieResults;
import com.example.babayaga.popularmovies.models.Results;
import com.example.babayaga.popularmovies.models.Reviews;
import com.example.babayaga.popularmovies.models.TrailerResults;
import com.example.babayaga.popularmovies.models.Trailers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mits on 7/9/16.
 */

public class JsonPArser {

    private MovieList mList = MovieList.getInstance();
    private ArrayList<MovieResults> results = new ArrayList<>();

    private Reviews reviews = Reviews.getInstance();
    private ArrayList<Results> reviewResults = new ArrayList<>();

    private  Trailers trailers = Trailers.getInstance();
    private  ArrayList<TrailerResults> trailerResults = new ArrayList<>();


    public MovieList setData(String s) {

        try {
            JSONObject jo = new JSONObject(s);
            String pages = jo.getString("page");
            JSONArray ja = jo.getJSONArray("results");
            MovieResults d;
            for(int i=0;i<ja.length();i++)
            {
                d=new MovieResults();
                JSONObject jo2 = ja.getJSONObject(i);
                d.setId(jo2.getString("id"));
                d.setTitle(jo2.getString("title"));
                d.setOverview(jo2.getString("overview"));
                d.setPoster_path(jo2.getString("poster_path"));
                d.setRelease_date(jo2.getString("release_date"));
                d.setVote_average(jo2.getString("vote_average"));
                d.setBack_path(jo2.getString("backdrop_path"));
                results.add(d);
            }
            mList.setResults(results);
            mList.setPage(pages);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mList;
    }
    public Reviews setReviewData(String s)
    {
        try {
        JSONObject jo = new JSONObject(s);
        String pages = jo.getString("page");
        String id = jo.getString("id");
        JSONArray ja = jo.getJSONArray("results");
        Results d;
        for(int i=0;i<ja.length();i++)
        {
            d=new Results();
            JSONObject jo2 = ja.getJSONObject(i);
            d.setId(jo2.getString("id"));
            d.setAuthor(jo2.getString("author"));
            d.setContent(jo2.getString("content"));
            d.setUrl(jo2.getString("url"));
            reviewResults.add(d);
        }
        reviews.setResults(reviewResults);
        reviews.setPage(pages);
        reviews.setId(id);

    } catch (JSONException e) {
        e.printStackTrace();
    }
        return reviews;

    }
    public Trailers setTrailerData(String s)
    {
        try {
            JSONObject jo = new JSONObject(s);
            String id = jo.getString("id");
            JSONArray ja = jo.getJSONArray("results");
            TrailerResults d;
            for(int i=0;i<ja.length();i++)
            {
                d=new TrailerResults();
                JSONObject jo2 = ja.getJSONObject(i);
                d.setId(jo2.getString("id"));
                d.setKey(jo2.getString("key"));
                d.setSite(jo2.getString("site"));
                d.setSize(jo2.getString("size"));
                trailerResults.add(d);
            }
            trailers.setResults(trailerResults);
            trailers.setId(id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailers;

    }

}
