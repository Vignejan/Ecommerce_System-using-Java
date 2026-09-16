package com.example.Ecommerce_Site.Entity;

import com.example.Ecommerce_Site.Model.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name="vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType type; // TWO_WHEELER, FOUR_WHEELER

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int yearStart;

    @Column(nullable = false)
    private int yearEnd;

    @ManyToMany(mappedBy = "compatibleVehicles")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
}

