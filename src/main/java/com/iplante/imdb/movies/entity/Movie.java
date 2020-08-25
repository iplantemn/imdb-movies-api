package com.iplante.imdb.movies.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Entity representing a movie.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/24/20
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int lengthMinutes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    private String synopsis;

    private String title;

    /**
     * Constructs a Movie entity.
     *
     * @param title the movie title.
     * @param releaseDate the movie's release date.
     * @param lengthMinutes the length of the movie in minutes.
     * @param synopsis a short synopsis of the movie's plot.
     */
    public Movie(String title, Date releaseDate, int lengthMinutes, String synopsis) {
        this.lengthMinutes = lengthMinutes;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.title = title;
    }
}
