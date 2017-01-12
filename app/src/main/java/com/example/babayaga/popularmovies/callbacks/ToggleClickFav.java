package com.example.babayaga.popularmovies.callbacks;

import android.database.Cursor;

import com.example.babayaga.popularmovies.models.MovieResults;

/**
 * Created by abhi on 1/12/17.
 */

public interface ToggleClickFav {

     void onToggleClick(MovieResults results, int size);
}
