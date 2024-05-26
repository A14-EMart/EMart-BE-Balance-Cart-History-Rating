package com.a14.emart.backendbchr.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class GetSupermarketResponse {
    private UUID id;
    private String name;
    private Long pengelola;
}