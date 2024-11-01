package com.low.level.design.moviebooking.service;

import com.low.level.design.moviebooking.domain.Movie;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MovieService {

    private List<Movie> movies;
    private Map<String, List<Movie>> moviesByLanguage;

    public void addMovieToMovieDatabase(Movie movie) {
        movies.add(movie);
    }

    public void removeMovieFromMovieDatabase(Movie movie) {
        movies.remove(movie);
        String language = movie.getMovieLanguage();
        if(moviesByLanguage.containsKey(language)) {
           List<Movie> movies = moviesByLanguage.get(language);
           movies.remove(movie);
           moviesByLanguage.put(language, movies);
        }
    }

    public List<Movie> getMoviesByPreferredLanguage(String language) {
        return moviesByLanguage.getOrDefault(language, Collections.emptyList());
    }
}
