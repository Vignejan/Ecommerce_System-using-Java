package com.example.Ecommerce_Site.Service;


import com.example.Ecommerce_Site.Entity.User;
import com.example.Ecommerce_Site.Model.Role;
import com.example.Ecommerce_Site.DTO.UserDTO;
import com.example.Ecommerce_Site.DTO.RequestDTO.UserRequestDTO;
import com.example.Ecommerce_Site.Repository.UserRepo;
import com.example.Ecommerce_Site.Service.Interface_Service.UserServiceInt;
import com.example.Ecommerce_Site.config.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInt {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUser() {
        if(userRepo.findAll().isEmpty()){
            throw new RuntimeException("No user found.Please add at least one user");
        }
        return userRepo.findAll().stream().
        map(userMapper::userToUserDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(UserRequestDTO userD) {
        User newuser=userRepo.save(userMapper.userRequestDTOToUser(userD,passwordEncoder));
        return userMapper.userToUserDTO(newuser);
    }

    @Override
    public UserDTO updateUserById(Long id, UserRequestDTO userD) {
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found with id "+id+" can't be updated"));
        user.setUsername(userD.getUsername());
        user.setEmail(userD.getEmail());
        user.setRole(Role.valueOf(userD.getRole()));
        user.setPassword(passwordEncoder.encode(userD.getPassword()));

        userRepo.save(user);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO getUser(Long id) {
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found with id "+id));
         return userMapper.userToUserDTO(user);
    }

    @Override
    public void deleteUserById(Long id) {
        if(userRepo.findById(id).isPresent()){
            userRepo.deleteById(id);
        }
        else{
            throw new RuntimeException("User not found with id "+id);
        }
    }
}
