package com.example.babayaga.popularmovies.parser;

import com.example.babayaga.popularmovies.models.MovieList;
import com.example.babayaga.popularmovies.models.MovieResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mits on 7/9/16.
 */

public class JsonPArser {
    public MovieList mList = MovieList.getInstance();
    ArrayList<MovieResults> results = new ArrayList<>();
    String pages;

    public MovieList setData(String s) {

        try {
            JSONObject jo = new JSONObject(s);
            pages = jo.getString("page");
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


}
