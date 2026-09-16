package com.example.Ecommerce_Site.DTO.RequestDTO;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private Long productId;
    private int quantity;
}
