package com.iplante.imdb.movies.service;

import com.iplante.imdb.movies.entity.Movie;
import com.iplante.imdb.movies.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link MovieService}
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    private final static Long MOVIE_ID = 1L;

    @InjectMocks
    private MovieService movieService;

    @Mock
    private CastClient castClient;

    @Mock
    private MovieRepository movieRepository;

    @Test
    void addMoviesSavesToRepository() {
        final var movieList = Arrays.asList(
                Movie.builder().title("Movie 1").build(),
                Movie.builder().title("Movie 2").build()
        );

        movieService.addMovies(movieList);
        verify(movieRepository).saveAll(eq(movieList));
    }

    @Test
    void getCastMoviesThrowsWhenMovieNotExists() {
        when(movieRepository.findById(MOVIE_ID)).thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> movieService.getMovieCast(MOVIE_ID, null)
        );
        verify(castClient, never()).getMovieCast(anyList(), any(Pageable.class));
    }

    @Test
    void getCastMoviesFetchesFromMoviesApi() {
        final var castIds = Arrays.asList(1L, 33L, 44L);
        final var pageable = Pageable.unpaged();

        when(movieRepository.findById(MOVIE_ID)).thenReturn(Optional.of(Movie.builder().cast(castIds).build()));

        movieService.getMovieCast(MOVIE_ID, pageable);
        verify(castClient).getMovieCast(eq(castIds), eq(pageable));
    }

    @Test
    void getMovieCountsReturnsTableCount() {
        when(movieRepository.count()).thenReturn(100L);
        assertThat(movieService.getMovieCount()).isEqualTo(100L);
        verify(movieRepository).count();
    }
}
