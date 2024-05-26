package com.a14.emart.backendbchr.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GetSupermarketResponse {
    private UUID id;
    private String name;
    private Long pengelola;

}