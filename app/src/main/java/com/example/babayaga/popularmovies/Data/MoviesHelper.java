package com.example.babayaga.popularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abhi on 8/23/16.
 */
public  class MoviesHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public MoviesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + MoviesContract.TABLE_NAME + " (" +

                MoviesContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                MoviesContract.TITLE + " INTEGER NOT NULL, " +
                MoviesContract.SYNOPSIS + " INTEGER NOT NULL, " +
                MoviesContract.AVERAGE + " TEXT NOT NULL, " +
                MoviesContract.DATE + " INTEGER NOT NULL," +
                MoviesContract.THUMBNAIL_IMG + " REAL NOT NULL, " +
                MoviesContract.BACK_IMG + " REAL NOT NULL )";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
