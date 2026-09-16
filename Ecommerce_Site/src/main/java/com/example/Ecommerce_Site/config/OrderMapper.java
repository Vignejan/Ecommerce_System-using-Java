package com.example.Ecommerce_Site.config;

import com.example.Ecommerce_Site.DTO.ResponseDTO.OrderItemResponseDTO;
import com.example.Ecommerce_Site.DTO.ResponseDTO.OrderResponseDTO;
import com.example.Ecommerce_Site.Entity.Order;
import com.example.Ecommerce_Site.Entity.User;
import com.example.Ecommerce_Site.Model.Address;
import com.example.Ecommerce_Site.DTO.OrderDTO;
import com.example.Ecommerce_Site.Entity.OrderItem;
import com.example.Ecommerce_Site.Model.OrderStatus;
import org.springframework.stereotype.Component;
import java.util.*;


import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO ordertoOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO(
                order.getId(),
                order.getUser().getId(),
                order.getUser().getUsername(),
                order.getOrderDate(),
                order.getStatus().toString(),
                order.getTotalPrice(),
                order.getShippingAddress().getId(),
                order.getItems().stream().map(item -> item.getProduct().getId()).collect(Collectors.toSet())
        );
        return orderDTO;
    }

    public Order orderDTOtoOrder(OrderDTO orderDTO, User user, Address shippingAddress, Set<OrderItem> orderItem){
        Order order = new Order();
        order.setId(orderDTO.getOrderId());
        order.setUser(user);
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(OrderStatus.valueOf(orderDTO.getOrderStatus()));
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setShippingAddress(shippingAddress);
        order.setItems(orderItem);
        return order;
    }

    public OrderResponseDTO ordertoOrderResponseDTO(Order order){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setCustomerId(order.getUser().getId());
        orderResponseDTO.setCustomerName(order.getUser().getUsername());
        Set<OrderItemResponseDTO> orderItems=order.getItems().
                stream().map(item->orderItemtoOrderItemResponseDTO(item)).collect(Collectors.toSet());
        orderResponseDTO.setItems(orderItems);
        orderResponseDTO.setOrderStatus(order.getStatus().toString());
        orderResponseDTO.setOrderTotalPrice(order.getTotalPrice());

        return orderResponseDTO;
    }

    public OrderItemResponseDTO orderItemtoOrderItemResponseDTO(OrderItem orderItem){
         OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
         orderItemResponseDTO.setProductName(orderItem.getProduct().getName());
         orderItemResponseDTO.setQuantity(orderItem.getQuantity());
         orderItemResponseDTO.setSubTotalPrice(orderItem.getSubTotalPrice());
         orderItemResponseDTO.setTotalPrice(orderItem.getPrice());
         return orderItemResponseDTO;
    }

}
