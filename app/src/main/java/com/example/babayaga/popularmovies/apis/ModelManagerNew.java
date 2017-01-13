package com.example.babayaga.popularmovies.apis;

import com.example.babayaga.popularmovies.models.MovieList;
import com.example.babayaga.popularmovies.models.Reviews;
import com.example.babayaga.popularmovies.models.TrailerResults;
import com.example.babayaga.popularmovies.models.Trailers;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by abhi on 1/13/17.
 */

public class ModelManagerNew {

    private ObjectMapper objectMapper = new ObjectMapper();
    private JsonFactory jsonFactory = new JsonFactory();

    public ModelManagerNew() {
    }

    private static ModelManagerNew constant;

    public static ModelManagerNew getInstance() {
        if (constant == null) {
            constant = new ModelManagerNew();
        }
        return constant;
    }

    // Home Page Store list Methods
    public MovieList getAllMovies(String jsonString) {
        MovieList getAllMovies = MovieList.getInstance();
        try {
            JsonParser jsonParser = jsonFactory.createParser(jsonString.trim());
            getAllMovies = objectMapper.readValue(jsonParser,  MovieList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getAllMovies;
    }

    public Reviews getReviews(String jsonString) {
        Reviews reviews = Reviews.getInstance();
        try {
            JsonParser jsonParser = jsonFactory.createParser(jsonString.trim());
            reviews = objectMapper.readValue(jsonParser,  Reviews.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }
    public Trailers getTrailers(String jsonString) {
        Trailers trailers = Trailers.getInstance();
        try {
            JsonParser jsonParser = jsonFactory.createParser(jsonString.trim());
            trailers = objectMapper.readValue(jsonParser,  Trailers.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trailers;
    }



}
