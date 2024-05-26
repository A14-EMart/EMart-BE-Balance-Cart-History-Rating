package com.a14.emart.backendbchr.DTO;

import lombok.Data;

@Data
public class GetProductResponse {
    private String id;
    private String name;
    private Long price;
    private int stock;
    private String imageUrl;
    private String supermarketId;
}
