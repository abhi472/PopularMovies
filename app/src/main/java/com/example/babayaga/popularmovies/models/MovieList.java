package com.example.babayaga.popularmovies.models;

import android.database.Cursor;

import com.example.babayaga.popularmovies.data.MoviesContract;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    private Integer page;
    private ArrayList<MovieResults> results = null;
    @JsonProperty("total_results")
    private Integer totalResults;
    @JsonProperty("total_pages")
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<MovieResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieResults> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }




    public static MovieResults fromCursor(Cursor cursor) {
        MovieResults movie = new MovieResults();
        movie.setId(
                Integer.parseInt(cursor.getString(cursor.getColumnIndex(MoviesContract._ID))));
        movie.setTitle(
                cursor.getString(cursor.getColumnIndex(MoviesContract.TITLE)));
        movie.setOverview(
                cursor.getString(cursor.getColumnIndex(MoviesContract.SYNOPSIS)));
        movie.setReleaseDate(
                cursor.getString(cursor.getColumnIndex(MoviesContract.DATE)));
        movie.setPosterPath(
                cursor.getString(cursor.getColumnIndex(MoviesContract.THUMBNAIL_IMG)));
        movie.setVoteAverage(
                Double.parseDouble(cursor.getString(cursor.getColumnIndex(MoviesContract.AVERAGE))));
        movie.setBackdropPath(
                cursor.getString(cursor.getColumnIndex(MoviesContract.BACK_IMG)));
        return movie;
    }
}
