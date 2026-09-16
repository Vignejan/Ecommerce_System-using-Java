package com.example.Ecommerce_Site.Entity;

import com.example.Ecommerce_Site.Model.Offer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int stock;

    private String brand;

    // 🔹 Many products belong to one seller
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    // 🔹 Many-to-Many with VehicleModel
    @ManyToMany
    @JoinTable(
            name = "product_vehicle",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private Set<Vehicle> compatibleVehicles = new HashSet<>();

    // 🔹 Many-to-Many with Offer
    @ManyToMany
    @JoinTable(
            name = "product_offer",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id")
    )
    private Set<Offer> offers = new HashSet<>();
}
