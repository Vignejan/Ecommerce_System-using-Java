package com.example.Ecommerce_Site.Model;

import com.example.Ecommerce_Site.Entity.Product;
import com.example.Ecommerce_Site.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    @ManyToOne
    @JsonIgnore // prevent infinite recursion
    private User user;

    @ManyToOne
    @JsonIgnore
    private Product product;
}
