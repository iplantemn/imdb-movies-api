package com.iplante.imdb.movies.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link Movie}.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
public class MovieTest {

    private static final int LENGTH_MINUTES = 121;
    private static final long ID = 1L;
    private static final Date RELEASE_DATE = new Date();
    private static final List<Long> CAST = Arrays.asList(1L, 2L, 3L);
    private static final List<String> GENRES = Arrays.asList("Comedy", "Other");
    private static final List<String> STUDIOS = Arrays.asList("Studio 1", "Studio 2");
    private static final String SYNOPSIS = "Jane Doe was born in Paris, France on January 10th, 1981";
    private static final String TITLE = "Jane";

    @Test
    void constructorTest() {
        final var movie = new Movie(ID, LENGTH_MINUTES, RELEASE_DATE, SYNOPSIS, TITLE, GENRES, STUDIOS, CAST);

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(ID);
        assertThat(movie.getLengthMinutes()).isEqualTo(LENGTH_MINUTES);
        assertThat(movie.getReleaseDate()).isEqualTo(RELEASE_DATE);
        assertThat(movie.getSynopsis()).isEqualTo(SYNOPSIS);
        assertThat(movie.getTitle()).isEqualTo(TITLE);
        assertThat(movie.getGenres()).isEqualTo(GENRES);
        assertThat(movie.getStudios()).isEqualTo(STUDIOS);
        assertThat(movie.getCast()).isEqualTo(CAST);
    }

    @Test
    void gettersAndSettersTest() {
        final var movie = new Movie();
        movie.setId(ID);
        movie.setLengthMinutes(LENGTH_MINUTES);
        movie.setReleaseDate(RELEASE_DATE);
        movie.setSynopsis(SYNOPSIS);
        movie.setTitle(TITLE);
        movie.setGenres(GENRES);
        movie.setStudios(STUDIOS);
        movie.setCast(CAST);

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(ID);
        assertThat(movie.getLengthMinutes()).isEqualTo(LENGTH_MINUTES);
        assertThat(movie.getReleaseDate()).isEqualTo(RELEASE_DATE);
        assertThat(movie.getSynopsis()).isEqualTo(SYNOPSIS);
        assertThat(movie.getTitle()).isEqualTo(TITLE);
        assertThat(movie.getGenres()).isEqualTo(GENRES);
        assertThat(movie.getStudios()).isEqualTo(STUDIOS);
        assertThat(movie.getCast()).isEqualTo(CAST);
    }

    @Test
    void builderTest() {
        final var movie = Movie
                .builder()
                .id(ID)
                .lengthMinutes(LENGTH_MINUTES)
                .releaseDate(RELEASE_DATE)
                .synopsis(SYNOPSIS)
                .title(TITLE)
                .genres(GENRES)
                .studios(STUDIOS)
                .cast(CAST)
                .build();

        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(ID);
        assertThat(movie.getLengthMinutes()).isEqualTo(LENGTH_MINUTES);
        assertThat(movie.getReleaseDate()).isEqualTo(RELEASE_DATE);
        assertThat(movie.getSynopsis()).isEqualTo(SYNOPSIS);
        assertThat(movie.getTitle()).isEqualTo(TITLE);
        assertThat(movie.getGenres()).isEqualTo(GENRES);
        assertThat(movie.getStudios()).isEqualTo(STUDIOS);
        assertThat(movie.getCast()).isEqualTo(CAST);
    }

    @Test
    void builderToStringTest() {
        final var movieBuilderString = Movie
                .builder()
                .id(ID)
                .lengthMinutes(LENGTH_MINUTES)
                .synopsis(SYNOPSIS)
                .title(TITLE)
                .toString();

        assertThat(movieBuilderString).isNotNull();
        assertThat(movieBuilderString).contains("id=" + ID);
        assertThat(movieBuilderString).contains("lengthMinutes=" + LENGTH_MINUTES);
        assertThat(movieBuilderString).contains("releaseDate=null");
        assertThat(movieBuilderString).contains("synopsis=" + SYNOPSIS);
        assertThat(movieBuilderString).contains("title=" + TITLE);
        assertThat(movieBuilderString).contains("genres=null");
        assertThat(movieBuilderString).contains("studios=null");
        assertThat(movieBuilderString).contains("cast=null");
    }
}
