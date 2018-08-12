package com.thoughtworks.sid.ut;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.sid.controller.ProductsController;
import com.thoughtworks.sid.domain.Product;
import com.thoughtworks.sid.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductsController.class)
public class ProductResourceTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    ProductsController productsController;

    @MockBean
    private ProductRepository productRepository;

    private final String BASE_URI = "http://localhost";
    private final String PRODUCT_URL = "/api/products";
    private final Long PRODUCT_ID = 1L;
    private final Product VALID_PRODUCT = new Product("product 1", "product 1 description");
    private final Product SAVED_PRODUCT = new Product(PRODUCT_ID, "product 1", "product 1 description");

    @Test
    public void should_success_to_create_product() throws Exception {
        when(productRepository.save(any(Product.class))).thenReturn(SAVED_PRODUCT);
        when(productRepository.getOne(PRODUCT_ID)).thenReturn(SAVED_PRODUCT);

        mockMvc.perform(createProduct(VALID_PRODUCT))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", BASE_URI + productUrl(PRODUCT_ID)));

        mockMvc.perform(getProduct(PRODUCT_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(SAVED_PRODUCT)))
                .andReturn();
    }

    @Test
    public void should_fail_to_find_inexist_product() throws Exception {
        when(productRepository.getOne(PRODUCT_ID)).thenReturn(null);

        mockMvc.perform(getProduct(PRODUCT_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_success_to_find_existing_product() throws Exception {
        when(productRepository.getOne(PRODUCT_ID)).thenReturn(VALID_PRODUCT);

        mockMvc.perform(getProduct(PRODUCT_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(VALID_PRODUCT)))
                .andReturn();
    }

    @Test
    public void should_success_to_get_product_list() throws Exception {
        List<Product> products = Collections.singletonList(SAVED_PRODUCT);
        when(productRepository.findAll()).thenReturn(products);

        mockMvc.perform(get(PRODUCT_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

    private RequestBuilder createProduct(Product product) throws JsonProcessingException {
        return post(PRODUCT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(product));
    }

    private RequestBuilder getProduct(Long productId) {
        return get(productUrl(productId));
    }

    private String productUrl(Long productId) {
        return PRODUCT_URL + "/" + productId;
    }
}
