package com.example.babayaga.popularmovies.Parser;

import com.example.babayaga.popularmovies.Models.MovieD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mits on 7/9/16.
 */

public class JsonPArser {
    ArrayList<MovieD> values = new ArrayList<>();

    public ArrayList<MovieD> setData(String s) {
        try {
            JSONObject jo = new JSONObject(s);
            JSONArray ja = jo.getJSONArray("results");
            MovieD d;
            for(int i=0;i<ja.length();i++)
            {
                d=new MovieD();
                JSONObject jo2 = ja.getJSONObject(i);
                d.id = jo2.getString("id");
                d.title = jo2.getString("title");
                d.overview = jo2.getString("overview");
                d.poster_path = jo2.getString("poster_path");
                d.release_date = jo2.getString("release_date");
                d.vote_average = jo2.getString("vote_average");
                d.back_path = jo2.getString("backdrop_path");
                values.add(d);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return values;
    }


}
