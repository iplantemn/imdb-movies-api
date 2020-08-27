package com.iplante.imdb.movies.service;

import com.iplante.imdb.movies.entity.Movie;
import com.iplante.imdb.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service in charge of custom CRUD operations against {@link Movie} entities.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    /**
     * Service constructor.
     *
     * @param movieRepository the {@link Movie} repository.
     */
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Add a list of {@link Movie} entities to the database.
     *
     * @param movieList the list of {@link Movie} entities.
     */
    public void addMovies(List<Movie> movieList) {
        movieRepository.saveAll(movieList);
    }

    /**
     * Get a count of all {@link Movie} entities in the database.
     *
     * @return a count of {@link Movie} entities.
     */
    public long getMovieCount() {
        return movieRepository.count();
    }
}
