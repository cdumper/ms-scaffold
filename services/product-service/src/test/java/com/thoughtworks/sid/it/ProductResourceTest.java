package com.thoughtworks.sid.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.sid.AbstractResourceIntegrationTest;
import com.thoughtworks.sid.domain.Product;
import org.junit.Test;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Collections;

public class ProductResourceTest extends AbstractResourceIntegrationTest {
    private final Product VALID_PRODUCT = new Product("product 1", "product 1 description");
    private final Product SAVED_PRODUCT = new Product(1L, "product 1", "product 1 description");

    @Test
    public void should_success_to_create_product() throws JsonProcessingException {
        webClient.post().uri("/api/products").body(BodyInserters.fromObject(VALID_PRODUCT))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo(mapper.writeValueAsString(SAVED_PRODUCT));
    }

    @Test
    public void should_success_to_get_created_product() throws JsonProcessingException {
        should_success_to_create_product();
        webClient.get().uri("/api/products")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo(mapper.writeValueAsString(Collections.singletonList(SAVED_PRODUCT)));
    }
}
