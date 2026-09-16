package com.example.Ecommerce_Site.config;

import com.example.Ecommerce_Site.Entity.User;
import com.example.Ecommerce_Site.Model.Role;
import com.example.Ecommerce_Site.DTO.UserDTO;
import com.example.Ecommerce_Site.DTO.RequestDTO.UserRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail(),
              user.getRole().name(),
              user.getAddresses().stream().map(adid->adid.getId()).collect(Collectors.toSet()),
              user.getProducts().stream().map(productId->productId.getId()).collect(Collectors.toSet())
                );
        return userDTO;
    }

    public User userDtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        if(userDTO.getRole()!=null) {
            user.setRole(Role.valueOf(userDTO.getRole()));
        }
        return  user;
    }

    public User userRequestDTOToUser(UserRequestDTO userRequestDTO, PasswordEncoder passwordEncoder ) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.valueOf(userRequestDTO.getRole()));
        return user;
    }
}
