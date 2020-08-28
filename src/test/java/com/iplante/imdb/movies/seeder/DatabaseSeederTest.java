package com.iplante.imdb.movies.seeder;

import com.iplante.imdb.movies.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link DatabaseSeeder}
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@ExtendWith(MockitoExtension.class)
public class DatabaseSeederTest {

    @InjectMocks
    private DatabaseSeeder databaseSeeder;

    @Mock
    private MovieService movieService;

    @Test
    void seedWhenCastCountIsMoreThanZero() {
        when(movieService.getMovieCount()).thenReturn(100L);
        databaseSeeder.seed(null);
        verify(movieService, never()).addMovies(anyList());
    }

    @Test
    void seedWhenCastCountIsZero() {
        final var captor = ArgumentCaptor.forClass(List.class);

        when(movieService.getMovieCount()).thenReturn(0L);
        databaseSeeder.seed(null);
        verify(movieService).addMovies(captor.capture());
        assertThat(captor.getValue()).hasSize(100);
    }
}
