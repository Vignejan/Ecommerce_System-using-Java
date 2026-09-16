package com.example.Ecommerce_Site.DTO;

import lombok.*;
import java.math.BigDecimal;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String sku;
    private String description;
    private BigDecimal price;
    private int stock;
    private String brand;
    private Long sellerId;
    private Set<Long> vehiclesIds = new HashSet<>();
    private Set<Long> offersIds = new HashSet<>();

}
