package com.example.babayaga.popularmovies.callbacks;

/**
 * Created by abhi on 1/13/17.
 */

public interface AsyncTaskCompleteListener {

    void onComplete(String str);

    void onComplete(String str, int requestId);

    void onError(Exception e);

    void onError(String msg, int requestId);

}

