package com.example.Ecommerce_Site.DTO;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String role; // CUSTOMER, SELLER, ADMIN
    private Set<Long> addressId = new HashSet<>();
    private Set<Long> productId = new HashSet<>();

}
