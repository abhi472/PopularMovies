package com.example.babayaga.popularmovies.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by abhi on 8/23/16.
 */
public class MovieProvider extends ContentProvider {

    private static final UriMatcher URI_MATCHER = buildUriMatcher();
    static final int MOVIES = 100;
    static final int MOVIE_BY_ID = 101;
    private MoviesHelper movieHelper;
    private static final String MOVIE_ID_SELECTION =
            MoviesContract.TABLE_NAME + "." + MoviesContract._ID + " = ? ";

    static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MoviesContract.CONTENT_AUTHORITY;
        uriMatcher.addURI(authority, MoviesContract.PATH_MOVIES, MOVIES);
        uriMatcher.addURI(authority, MoviesContract.PATH_MOVIES + "/#", MOVIE_BY_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {

        movieHelper = new MoviesHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int match = URI_MATCHER.match(uri);
        Cursor cursor;
        switch(match)
        {
            case MOVIES:
                cursor = movieHelper.getReadableDatabase().query(
                    MoviesContract.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );
                break;
            case MOVIE_BY_ID:
                cursor = getMovieById(uri, projection, sortOrder);
                break;
            default:
                return null;
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case MOVIES:
                return MoviesContract.CONTENT_TYPE;
            case MOVIE_BY_ID:
                return MoviesContract.CONTENT_ITEM_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = movieHelper.getWritableDatabase();
        final int match = URI_MATCHER.match(uri);
        Uri returnUri=null;
        long id;
        switch (match) {
            case MOVIES:
                id = db.insert(MoviesContract.TABLE_NAME,null,contentValues);
                if(id>0)
                {
                    returnUri = MoviesContract.buildMovieUri(id);
                }
                else
                throw new SQLiteException("failed to inser row into " + uri);
                break;
            case MOVIE_BY_ID:
                returnUri = null;
                break;
            default:
                throw new UnsupportedOperationException("unknown uri " + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = movieHelper.getWritableDatabase();
        final int match = URI_MATCHER.match(uri);
        int rowsDeleted;
        switch (match) {
            case MOVIES:
                rowsDeleted = db.delete(MoviesContract.TABLE_NAME, selection, selectionArgs);
                break;
            case MOVIE_BY_ID:
                long id = MoviesContract.getIdFromUri(uri);
                rowsDeleted = db.delete(MoviesContract.TABLE_NAME,
                        MOVIE_ID_SELECTION, new String[]{Long.toString(id)});

                break;default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        SQLiteDatabase db = movieHelper.getWritableDatabase();
        final int match = URI_MATCHER.match(uri);
        int rowsUpdated;
        switch (match) {
            case MOVIES:
                rowsUpdated = db.update(MoviesContract.TABLE_NAME, contentValues,
                        selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
    private Cursor getMovieById(Uri uri, String[] projection, String sortOrder) {
        long id = MoviesContract.getIdFromUri(uri);
        String selection = MOVIE_ID_SELECTION;
        String[] selectionArgs = new String[]{Long.toString(id)};
        return movieHelper.getReadableDatabase().query(
                MoviesContract.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }
    @Override
    public void shutdown() {
        movieHelper.close();
        super.shutdown();
    }
}
