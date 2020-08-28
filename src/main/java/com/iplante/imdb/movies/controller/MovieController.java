package com.iplante.imdb.movies.controller;

import com.iplante.imdb.movies.entity.Movie;
import com.iplante.imdb.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller exposing custom CRUD operations on {@link Movie} that Spring Data REST cannot handle.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
@RepositoryRestController
public class MovieController {

    private final MovieService movieService;

    /**
     * Controller constructor.
     *
     * @param movieService the service in charge of business logic for {@link Movie} CRUD operations.
     */
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Get all cast credited in a given {@link Movie}.
     *
     * @param movieId the ID of the {@link Movie}
     * @param pageable the optional Pageable query parameters.
     * @return a list of cast credited in a given {@link Movie}.
     */
    @GetMapping(path = "movies/{movieId}/cast")
    public ResponseEntity<Object> getMovieCast(@PathVariable long movieId, Pageable pageable) {
        return ResponseEntity.ok(movieService.getMovieCast(movieId, pageable));
    }
}
