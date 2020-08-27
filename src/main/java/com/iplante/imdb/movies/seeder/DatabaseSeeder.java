package com.iplante.imdb.movies.seeder;

import com.iplante.imdb.movies.entity.Movie;
import com.iplante.imdb.movies.service.MovieService;
import com.iplante.imdb.movies.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * On app startup, seed the database if necessary.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
@Component
public class DatabaseSeeder {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DatabaseSeeder.class));

    private static final List<String> GENRES = Arrays.asList(
            "Comedy", "Science fiction", "Drama", "Animated", "Foreign", "Action", "Superhero", "Independent"
    );

    private static final List<String> STUDIOS = Arrays.asList(
            "Walt Disney Studio", "Warner Bros", "Universal Studios", "Lucasfilm", "20th Century Studios",
            "Columbia Pictures", "Paramount Pictures", "MGM", "Miramax Films", "Castle Rock Entertainment",
            "Marvel Studios", "DC Films"
    );

    private final MovieService movieService;

    /**
     * Constructs the database seeder.
     *
     * @param movieService the service responsible for {@link Movie} CRUD operations.
     */
    @Autowired
    public DatabaseSeeder(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * This method is called on application startup and will seed the database if needed.
     *
     * @param event ignored.
     */
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (movieService.getMovieCount() == 0) {
            LOGGER.info("Seeding database");
            populateTables(100);
            LOGGER.info("Done seeding database");
        } else {
            LOGGER.info("Database already contains data - skipping seeding step");
        }
    }

    /**
     * Populate the database tables with a given amount of rows.
     *
     * @param seed the number of {@link Movie} entries to generate.
     */
    private void populateTables(int seed) {
        final var movies = IntStream
                .range(0, seed)
                .mapToObj(this::generateMovie)
                .collect(Collectors.toList());

        movieService.addMovies(movies);
    }

    /**
     * Generate a {@link Movie} object with random data.
     *
     * @param num ignored parameter.
     * @return a {@link Movie} member.
     */
    private Movie generateMovie(int num) {
        return Movie
                .builder()
                .title(RandomUtil.generateRandomWords(1, 4, 2, 8))
                .lengthMinutes(RandomUtil.generateRandomInt(60, 240))
                .releaseDate(RandomUtil.generateRandomDate(1930, 2020)) // todo fix
                .genres(Arrays.asList(
                        GENRES.get(RandomUtil.generateRandomInt(0, GENRES.size() - 1)),
                        GENRES.get(RandomUtil.generateRandomInt(0, GENRES.size() - 1))
                ))
                .studios(Arrays.asList(
                        STUDIOS.get(RandomUtil.generateRandomInt(0, STUDIOS.size())),
                        STUDIOS.get(RandomUtil.generateRandomInt(0, STUDIOS.size()))
                ))
                // Expecting that 2000 cast have een generated in the cast database.
                .cast(RandomUtil.generateRandomListOfLongs(10, 30, 1, 2000))
                .synopsis(RandomUtil.generateRandomWords(10, 40, 2, 8))
                .build();
    }
}
