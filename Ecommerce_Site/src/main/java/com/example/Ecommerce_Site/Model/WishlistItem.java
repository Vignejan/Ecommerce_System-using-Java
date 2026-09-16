package com.example.Ecommerce_Site.Model;

import com.example.Ecommerce_Site.Entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Wishlist wishlist;

    @ManyToOne
    private Product product;
}
