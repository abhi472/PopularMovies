package com.example.babayaga.popularmovies.callbacks;

/**
 * Created by abhi on 1/13/17.
 */

public interface ICallBack {
    void onRecieve(String str);
    void onRecieve(String str,int icallbackId);
    void onError(String str);
    void onError(String str,int id);
}
