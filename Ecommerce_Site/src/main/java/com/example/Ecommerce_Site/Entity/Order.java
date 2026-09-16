package com.example.Ecommerce_Site.Entity;

import com.example.Ecommerce_Site.Model.Address;
import com.example.Ecommerce_Site.Model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private LocalDateTime orderDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, SHIPPED, DELIVERED
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name="address_id")
    @JsonIgnore
    private Address shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();
}
