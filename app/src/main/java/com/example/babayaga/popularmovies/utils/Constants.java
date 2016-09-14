package com.example.babayaga.popularmovies.utils;

/**
 * Created by abhishek on 14/9/16.
 */

public class Constants {

    private String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private String API_KEY = "api_key=7a83a78b4e9a7bdbd184461cedb61494";
    private static Constants constants;

    public static Constants getInstance()
    {
        if(constants ==  null)
        {
            constants = new Constants();
        }//http://api.themoviedb.org/3/movie/top_rated?api_key=7a83a78b4e9a7bdbd184461cedb61494
        return constants;
    }

    public String popularMoviesApi()
    {
       return BASE_URL + "popularity?" + API_KEY;
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
}
