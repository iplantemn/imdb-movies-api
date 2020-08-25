package com.iplante.imdb.movies.repository;

import com.iplante.imdb.movies.entity.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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
}
