package com.example.Ecommerce_Site.DTO.ResponseDTO;

import com.example.Ecommerce_Site.Model.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.*;

@Data
public class OrderResponseDTO {
    private Long customerId;
    private String customerName;
    private String orderStatus;
    private BigDecimal orderTotalPrice;
    private Set<OrderItemResponseDTO> items;
}
