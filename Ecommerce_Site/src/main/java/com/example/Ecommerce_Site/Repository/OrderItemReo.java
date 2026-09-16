package com.example.Ecommerce_Site.Repository;

import com.example.Ecommerce_Site.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemReo extends JpaRepository<OrderItem,Integer> {
}
