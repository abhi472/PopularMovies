package com.example.babayaga.popularmovies.models;

import java.util.ArrayList;

/**
 * Created by abhi on 9/13/16.
 */
public class Trailers {

    private static Trailers trailers;

    public static Trailers getInstance() {
        if(trailers!=null)
            return trailers;
        else
        {
            trailers = new Trailers();
            return trailers;
        }
    }

    private String id;
    private ArrayList<TrailerResults> results = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<TrailerResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<TrailerResults> results) {
        this.results = results;
    }
}
