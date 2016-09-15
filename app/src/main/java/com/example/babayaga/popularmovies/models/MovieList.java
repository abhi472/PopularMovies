package com.example.babayaga.popularmovies.models;

import java.util.ArrayList;

/**
 * Created by abhi on 9/13/16.
 */
public class MovieList {

    private static MovieList mList;

    public static MovieList getInstance() {
        if(mList!=null)
        return mList;
        else
        {
            mList = new MovieList();
            return mList;
        }
    }

    public String page;
    public ArrayList<MovieResults> results = new ArrayList<>();

    public void setResults(ArrayList<MovieResults> results)
    {
        this.results = results;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<MovieResults> getResults() {
        return results;
    }

    public String getPage() {
        return page;
    }
}
