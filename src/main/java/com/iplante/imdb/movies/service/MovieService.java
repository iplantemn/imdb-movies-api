package com.iplante.imdb.movies.service;

import com.iplante.imdb.movies.entity.Movie;
import com.iplante.imdb.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    private final CastClient castClient;
    private final MovieRepository movieRepository;

    /**
     * Service constructor.
     *
     * @param castClient      the REST client to interact with the Cast API.
     * @param movieRepository the {@link Movie} repository.
     */
    @Autowired
    public MovieService(CastClient castClient, MovieRepository movieRepository) {
        this.castClient = castClient;
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
     * Get all cast members for a given {@link Movie}.
     *
     * @param movieId  the ID of the {@link Movie}.
     * @param pageable the optional Pageable query parameters.
     * @return Object containing a list of cast member for the given {@link Movie}. We return Object to just
     * act as a proxy and return exactly what we got.
     * @throws EntityNotFoundException if no {@link Movie} found for given ID.
     */
    public Object getMovieCast(long movieId, Pageable pageable) {
        final var movie = movieRepository.findById(movieId);

        if (movie.isPresent()) {
            final var castIds = movie.get().getCast();
            return castClient.getMovieCast(castIds, pageable);

        } else {
            throw new EntityNotFoundException("Movie not found: movieId=" + movieId);

        }
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
