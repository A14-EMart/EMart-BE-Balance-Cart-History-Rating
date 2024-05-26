package com.a14.emart.backendbchr.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Supermarket {
    private UUID id;
    private String name;
    private String description;
    private Long pengelola;
    private Double rating;
    private String imageUrl;
}
