package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.dto.GetProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductService {
    private final WebClient webClient;
    public ProductService(@Value("${spring.route.gateway_url}") String productUrl) {
        this.webClient = WebClient.builder().baseUrl(productUrl).build();
    }
//    public List<GetProductResponse> getAllProduct(Long supermarketId) {
//        return webClient.get()
//                .uri("/get-all/{supermarketId}", supermarketId)
//                .retrieve()
//                .bodyToFlux(GetProductResponse.class)
//                .collectList()
//                .block();
//    }

    public GetProductResponse getProductById(String productId) {
        return webClient.get()
                .uri("/product/findById/{id}", productId)
                .retrieve()
                .bodyToMono(GetProductResponse.class)
                .block();
    }

}