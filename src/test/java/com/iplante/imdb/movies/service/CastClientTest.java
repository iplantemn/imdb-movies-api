package com.iplante.imdb.movies.service;

import com.iplante.imdb.movies.configuration.CastApiConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link CastClient}.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
@ExtendWith(MockitoExtension.class)
public class CastClientTest {

    @InjectMocks
    private CastClient castClient;

    @Mock
    private CastApiConfiguration castApiConfiguration;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void getMovieCast(@Mock ResponseEntity<Object> responseEntity) {
        final var captor = ArgumentCaptor.forClass(String.class);
        final var pageable = PageRequest.of(4, 10, Sort.unsorted());

        when(responseEntity.getBody()).thenReturn("Mocked body");
        when(restTemplate.getForEntity(anyString(), eq(Object.class))).thenReturn(responseEntity);

        castClient.getMovieCast(Arrays.asList(1L, 2L, 3L), pageable);
        verify(restTemplate).getForEntity(captor.capture(), eq(Object.class));

        final var uri = captor.getValue();
        assertThat(uri).contains("castIds=1,2,3");
        assertThat(uri).contains("size=10");
        assertThat(uri).contains("page=4");
    }
}
