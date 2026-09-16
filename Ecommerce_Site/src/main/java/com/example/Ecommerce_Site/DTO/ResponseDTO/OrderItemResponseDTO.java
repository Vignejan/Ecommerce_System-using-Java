package com.example.Ecommerce_Site.DTO.ResponseDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponseDTO {
   private String productName;
   private Integer quantity;
   private BigDecimal totalPrice;
   private BigDecimal subTotalPrice;
}
