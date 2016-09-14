package com.example.babayaga.popularmovies.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.babayaga.popularmovies.data.MoviesContract;
import com.example.babayaga.popularmovies.models.MovieResults;

/**
 * Created by abhi on 9/4/16.
 */
public class FavoriteAdder  {

    private final Context context;
    private static FavoriteAdder instance = null;
    public FavoriteAdder(Context context)
    {
        this.context = context;
    }
    public static FavoriteAdder getinstance(Context context)
    {
        if(instance == null)
        {
            instance = new FavoriteAdder(context);
        }
        return instance;

    }

    public  void addFav(MovieResults movie)
    {
       ContentValues cv = new ContentValues();
        cv.put(MoviesContract._ID,movie.id);
        cv.put(MoviesContract.TITLE,movie.title);
        cv.put(MoviesContract.DATE,movie.release_date);
        cv.put(MoviesContract.AVERAGE,movie.vote_average);
        cv.put(MoviesContract.SYNOPSIS,movie.overview);
        cv.put(MoviesContract.THUMBNAIL_IMG,movie.poster_path);
        cv.put(MoviesContract.BACK_IMG,movie.back_path);
        context.getContentResolver().insert(MoviesContract.CONTENT_URI,cv);
    }
    public void remFav(MovieResults movie) {
        context.getContentResolver().delete(
                MoviesContract.CONTENT_URI,
                MoviesContract._ID + " = " + movie.id,
                null
        );
    }
    public boolean isFav(MovieResults movie)
    {
       Cursor cursor = context.getContentResolver().query(MoviesContract.CONTENT_URI,null,MoviesContract._ID+" = "+movie.id,null,null);
        if(cursor.moveToFirst())
        {

            Log.d("check", "isFav: "+ cursor.getCount()+ "  " +movie.id);
            return false;
        }

        else {
            Log.d("check", "isFav: no ");
            return true;
        }
    }
}
