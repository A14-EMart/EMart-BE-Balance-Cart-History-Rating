package com.a14.emart.backendbchr.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GetProductResponse {
    public UUID id;
    public String name;
    public Long price;
    public Integer stock;
}