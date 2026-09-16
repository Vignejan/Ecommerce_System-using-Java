package com.example.Ecommerce_Site.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    @ManyToMany(mappedBy = "offers")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
}
