package com.example.babayaga.popularmovies.models;

import android.database.Cursor;

import com.example.babayaga.popularmovies.data.MoviesContract;

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

    public static MovieResults fromCursor(Cursor cursor) {
        MovieResults movie = new MovieResults();
        movie.setId(
                cursor.getString(cursor.getColumnIndex(MoviesContract._ID)));
        movie.setTitle(
                cursor.getString(cursor.getColumnIndex(MoviesContract.TITLE)));
        movie.setOverview(
                cursor.getString(cursor.getColumnIndex(MoviesContract.SYNOPSIS)));
        movie.setRelease_date(
                cursor.getString(cursor.getColumnIndex(MoviesContract.DATE)));
        movie.setPoster_path(
                cursor.getString(cursor.getColumnIndex(MoviesContract.THUMBNAIL_IMG)));
        movie.setVote_average(
                cursor.getString(cursor.getColumnIndex(MoviesContract.AVERAGE)));
        movie.setBack_path(
                cursor.getString(cursor.getColumnIndex(MoviesContract.BACK_IMG)));
        return movie;
    }
}
