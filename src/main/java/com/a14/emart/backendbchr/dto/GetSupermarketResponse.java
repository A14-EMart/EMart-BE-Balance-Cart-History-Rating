package com.a14.emart.backendbchr.dto;

import java.util.UUID;

public class GetSupermarketResponse {
    private UUID id;
    private String name;
    private Long pengelola;
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getPengelola() {
        return pengelola;
    }

    public void setPengelola(Long pengelola) {
        this.pengelola = pengelola;
    }
}