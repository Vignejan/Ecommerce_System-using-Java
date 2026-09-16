package com.example.Ecommerce_Site.Controller;

import com.example.Ecommerce_Site.DTO.UserDTO;
import com.example.Ecommerce_Site.DTO.RequestDTO.UserRequestDTO;
import com.example.Ecommerce_Site.Service.Interface_Service.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceInt userService;

    @GetMapping
    public List<UserDTO> getAllUser(){
        return  userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public UserDTO addUser(@RequestBody UserRequestDTO user){
        return userService.createUser(user);

    }

    @PutMapping("/{id}")
    public  UserDTO updateUserById(@PathVariable Long id, @RequestBody UserRequestDTO user){
        return  userService.updateUserById(id,user);

    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ("User deleted successfully "+id);
    }

//    @DeleteMapping("/clear")
//    public String deleteAllUser(@RequestBody User user){
//        userService.deleteAllUser(user);
//        return ("All User deleted successfully");
//    }
}
