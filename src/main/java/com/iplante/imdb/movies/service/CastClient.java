package com.iplante.imdb.movies.service;

import com.iplante.imdb.movies.configuration.CastApiConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Client for the Cast API.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@Component
public class CastClient {
    private static final String CAST_API_PATH = "/api/v1/cast/search/ids";

    private final RestTemplate restTemplate;

    private final String castApiUrl;

    /**
     * Client constructor.
     *
     * @param castApiConfiguration the Cast API configuration.
     * @param restTemplate         custom {@link RestTemplate}.
     */
    @Autowired
    public CastClient(CastApiConfiguration castApiConfiguration, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        castApiUrl = castApiConfiguration.getBaseUrl() + CAST_API_PATH;
    }

    /**
     * Make an API call to Cast API to get a list of cast for given IDs.
     *
     * @param castIds  a list of cast IDs
     * @param pageable pageable attributes.
     * @return Object - so we can just be a pass-through and return the data as-is.
     */
    public Object getMovieCast(List<Long> castIds, Pageable pageable) {
        final var uri = UriComponentsBuilder
                .fromUriString(castApiUrl)
                .queryParam("castIds", castIds.toString().replace("[", "").replace("]", "").replace(" ", ""))
                .queryParam("size", pageable.getPageSize())
                .queryParam("page", pageable.getPageNumber())
                .toUriString();

        final var response = restTemplate.getForEntity(uri, Object.class);
        return response.getBody();
    }

}
