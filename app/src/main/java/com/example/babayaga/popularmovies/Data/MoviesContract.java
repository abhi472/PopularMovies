package com.example.babayaga.popularmovies.Data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;

/**
 * Created by abhi on 8/23/16.
 */
public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "com.example.babayaga.popularmovies";
    public static final String TABLE_NAME = "fav_movies";
    public static final String PATH_MOVIES = "movies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY ;
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY ;
    public static final String TITLE = "title";
    public static final String SYNOPSIS = "overview";
    public static final String POPULARITY = "popularity";
    public static final String AVERAGE = "vote_average";
    public static final String DATE = "release_date";
    public static final String THUMBNAIL_IMG = "poster_path";
    public static final String BACK_IMG = "back_path";
    public static final String _ID = "id";

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();


    private static final String[] COLUMNS = {_ID, TITLE, SYNOPSIS,
            DATE, THUMBNAIL_IMG, POPULARITY,
            AVERAGE, BACK_IMG};
    public static Uri buildMovieUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static long getIdFromUri(Uri uri) {
        return ContentUris.parseId(uri);
    }

    public static String[] getColumns() {
        return COLUMNS.clone();
    }
}
