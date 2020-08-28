package com.iplante.imdb.movies.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Configure {@link RestTemplate} to deserialize HAL JSON automatically. This is necessary because
 * Spring Data REST uses HATEOAS delivers data through hypermedia.
 * <p>
 * See: <a href="https://gist.github.com/ripla/6f1516e3d0c28f4d591303d4060342d4">Source</a>
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@Configuration
public class RestConfiguration {

    /**
     * Configure {@link RestTemplate} to support HAL JSON.
     *
     * @param restTemplateBuilder the builder.
     * @return configured {@link RestTemplate}.
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        final var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Jackson2HalModule());

        final var messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setSupportedMediaTypes(Collections.singletonList(MediaTypes.HAL_JSON));
        messageConverter.setObjectMapper(objectMapper);

        return restTemplateBuilder.messageConverters(messageConverter).build();
    }
}
