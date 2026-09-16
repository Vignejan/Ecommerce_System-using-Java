package com.example.Ecommerce_Site.Repository;

import com.example.Ecommerce_Site.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Long> {

}
