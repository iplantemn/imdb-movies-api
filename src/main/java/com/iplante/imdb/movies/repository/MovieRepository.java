package com.iplante.imdb.movies.repository;

import com.iplante.imdb.movies.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Repository for {@link Movie} entities, additionally exposing CRUD operations as pageable and
 * sortable RESTful API.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/24/20
 */
@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    /**
     * Find all movies for a given cast member.
     *
     * @param castId   the ID of the cast.
     * @param pageable the pageable parameters.
     * @return a list of movies attributed to the cast member.
     */
    @RestResource(path = "castId")
    Page<Movie> findAllByCast(@Param("castId") long castId, Pageable pageable);

}