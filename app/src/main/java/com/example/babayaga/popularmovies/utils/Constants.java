package com.example.babayaga.popularmovies.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.babayaga.popularmovies.BuildConfig;
/**
 * Created by abhishek on 14/9/16.
 */

public class Constants {

    private final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private final String API_KEY = "api_key=" + BuildConfig.TMDB_API_KEY;
    private static Constants constants;


    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Constants getInstance()
    {
        if(constants ==  null)
        {
            constants = new Constants();
        }
        return constants;
    }

    public String popularMoviesApi()
    {
       return BASE_URL + "popular?" + API_KEY;
    }
    public String ratedMoviesApi()
    {
        return BASE_URL + "top_rated?" + API_KEY;
    }
    public String imageApi(String val,String size)
    {
        String api="";
        switch(size)
        {
            case "320" : api = "http://image.tmdb.org/t/p/w320/" + val;
                break;
            case "500" : api = "http://image.tmdb.org/t/p/w500/" + val;
                break;
            case "185" : api = "http://image.tmdb.org/t/p/w185/" + val;
                break;
            default    : return "http://image.tmdb.org/t/p/w320/" + val;
        }
        return api;

    }
    public String trailerApi(String id)
    {
        return BASE_URL + id +"/videos?" + API_KEY;
    }
    public String reviewApi(String id)
    {
        return BASE_URL + id +"/reviews?" + API_KEY;
    }
    public String thumbNail(String id) {return "http://img.youtube.com/vi/"+id+"/mqdefault.jpg";}
}
