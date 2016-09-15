package com.example.babayaga.popularmovies.models;

import java.util.ArrayList;

/**
 * Created by abhi on 9/13/16.
 */
public class Reviews {

    private static Reviews reviews;

    public static Reviews getInstance() {
        if(reviews!=null)
            return reviews;
        else
        {
            reviews = new Reviews();
            return reviews;
        }
    }

    private String id;
    private String page;
    private ArrayList<Results> results = new ArrayList<>();
    private String total_pages;
    private String total_results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }
}
