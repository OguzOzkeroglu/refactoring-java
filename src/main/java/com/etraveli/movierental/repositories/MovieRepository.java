package com.etraveli.movierental.repositories;

import com.etraveli.movierental.models.Movie;
import com.etraveli.movierental.models.MovieType;

import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    private static final Map<String, Movie> movies = new HashMap<>();

    static {
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
        movies.put("F003", new Movie("Cars", MovieType.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW));
    }

    public Movie getMovieById(String movieId) {
        return movies.get(movieId);
    }
}
