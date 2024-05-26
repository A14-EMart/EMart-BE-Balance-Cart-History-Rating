package com.a14.emart.backendbchr.rest;

import com.a14.emart.backendbchr.dto.GetSupermarketResponse;
import com.a14.emart.backendbchr.dto.SupermarketApiResponse;
import com.a14.emart.backendbchr.models.Supermarket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class SupermarketService {
    private final WebClient webClient;

    public SupermarketService(@Value("${spring.route.gateway_url}") String supermarketUrl) {
        this.webClient = WebClient.builder().baseUrl(supermarketUrl).build();
    }

    public GetSupermarketResponse getSupermarketById(UUID supermarketId) {
        SupermarketApiResponse apiResponse =  webClient.get()
                .uri("/supermarket/{id}", supermarketId)
                .retrieve()
                .bodyToMono(SupermarketApiResponse.class)
                .block();

        if (apiResponse != null && apiResponse.isSuccess()) {
            return mapToDto(apiResponse.getData());
        }
        else {
            throw new RuntimeException("Supermarket not found or API call failed");
        }
    }

    private GetSupermarketResponse mapToDto(Supermarket data) {
        GetSupermarketResponse response = new GetSupermarketResponse();
        response.setId(data.getId());
        response.setName(sanitizeName(data.getName()));
        response.setPengelola(data.getPengelola());
        return response;
    }

    private String sanitizeName(String name) {
        if (name.startsWith("\"") && name.endsWith("\"")) {
            return name.substring(1, name.length() - 1);
        }
        return name;
    }
}
