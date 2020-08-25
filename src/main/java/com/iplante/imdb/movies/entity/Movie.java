package com.iplante.imdb.movies.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.Date;
import java.util.List;

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
@AllArgsConstructor
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

    @ElementCollection
    @CollectionTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genres;

    @ElementCollection
    @CollectionTable(name = "movie_studio", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "studio")
    private List<String> studios;
}
