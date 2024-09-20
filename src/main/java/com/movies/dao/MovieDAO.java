package com.movies.dao;

import com.movies.model.Movie;

public interface MovieDAO {
    void addMovie(Movie movie);
    Movie getMovieById(int id);
    void updateMovie(Movie movie);
    void deleteMovie(int id);
}
