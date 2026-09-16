package com.example.Ecommerce_Site.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Long customerId;
    private String customerName;
    private LocalDateTime orderDate;
    private String orderStatus; //Shipped,Delivered,Pending
    private BigDecimal totalPrice;
    private Long addressId;
    private Set<Long> productIds=new HashSet<>();
}
