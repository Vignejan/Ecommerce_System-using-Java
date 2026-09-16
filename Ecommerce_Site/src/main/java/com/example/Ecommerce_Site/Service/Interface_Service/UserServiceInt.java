package com.example.Ecommerce_Site.Service.Interface_Service;

import com.example.Ecommerce_Site.DTO.UserDTO;
import com.example.Ecommerce_Site.DTO.RequestDTO.UserRequestDTO;

import java.util.List;

public interface UserServiceInt {
    List<UserDTO> getAllUser();
    public UserDTO createUser(UserRequestDTO user);
    public UserDTO updateUserById(Long id, UserRequestDTO user);
    UserDTO getUser(Long id);
    void deleteUserById(Long id);

}
