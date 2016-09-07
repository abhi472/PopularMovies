package com.example.babayaga.popularmovies.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Movie;

import com.example.babayaga.popularmovies.Data.MoviesContract;
import com.example.babayaga.popularmovies.Models.MovieD;

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

    public  void addFav(MovieD movie)
    {
       ContentValues cv = new ContentValues();
        cv.put(MoviesContract._ID,movie.id);
        context.getContentResolver().insert(MoviesContract.CONTENT_URI,cv);
    }
    public void remFav(MovieD movie) {
        context.getContentResolver().delete(
                MoviesContract.CONTENT_URI,
                MoviesContract._ID + " = " + movie.id,
                null
        );
    }
    public boolean isFav(MovieD movie)
    {
       Cursor cursor = context.getContentResolver().query(MoviesContract.CONTENT_URI,null,MoviesContract._ID+" = "+movie.id,null,null);
        if(cursor.getCount()<=0)
        {
            return false;
        }
        else return true;
    }
}
