package com.a14.emart.backendbchr.models;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    UUID id;
    String name;
    Long price;
}
