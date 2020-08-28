package com.iplante.imdb.movies.controller;

import com.iplante.imdb.movies.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link MovieController}.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/28/20
 */
@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {
    private final static long MOVIE_ID = 1L;

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @Test
    void getCastMoviesTest() {
        final var pageable = Pageable.unpaged();
        when(movieService.getMovieCast(MOVIE_ID, pageable)).thenReturn("Mocked body");

        final var result = movieController.getMovieCast(MOVIE_ID, pageable);
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("Mocked body");
    }
}
