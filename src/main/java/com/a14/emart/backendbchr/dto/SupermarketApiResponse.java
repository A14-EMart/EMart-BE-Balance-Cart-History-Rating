package com.a14.emart.backendbchr.dto;

import com.a14.emart.backendbchr.models.Supermarket;
import lombok.Data;

@Data
public class SupermarketApiResponse {
    private boolean success;
    private Supermarket data;
    private String message;
}
