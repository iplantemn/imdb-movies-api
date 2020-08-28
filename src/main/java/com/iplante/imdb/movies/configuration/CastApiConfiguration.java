package com.iplante.imdb.movies.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration object for the Cast API.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "cast-api")
public class CastApiConfiguration {

    /**
     * The base URL of the Cast API.
     */
    private String baseUrl;
}
