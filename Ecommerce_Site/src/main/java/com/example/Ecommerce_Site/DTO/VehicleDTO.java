package com.example.Ecommerce_Site.DTO;

import com.example.Ecommerce_Site.Model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private Long id;
    private VehicleType vehicleType;
    private String description;
    private String model;
    private double price;
    private int yearStart;
    private int yearEnd;

    private Set<Long> productId = new HashSet<>();
}
