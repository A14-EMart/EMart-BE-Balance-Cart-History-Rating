package com.a14.emart.backendbchr.rest;

import com.a14.emart.backendbchr.dto.GetProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class ProductService {
    private final WebClient webClient;

    public ProductService(@Value("${spring.route.gateway_url}") String productUrl) {
        this.webClient = WebClient.builder().baseUrl(productUrl).build();
    }

    public GetProductResponse getProductById(UUID productId) {
        return webClient.get()
                .uri("/product/findById/{id}", productId)
                .retrieve()
                .bodyToMono(GetProductResponse.class)
                .block();
    }
}
