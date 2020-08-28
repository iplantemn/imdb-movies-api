package com.iplante.imdb.movies.configuration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link CastApiConfiguration}.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/28/20
 */
public class CastApiConfigurationTest {

    private final CastApiConfiguration castApiConfiguration = new CastApiConfiguration();

    @Test
    void testGetSetBaseUrl() {
        castApiConfiguration.setBaseUrl("http://test.com");
        assertThat(castApiConfiguration.getBaseUrl()).isEqualTo("http://test.com");
    }
}
