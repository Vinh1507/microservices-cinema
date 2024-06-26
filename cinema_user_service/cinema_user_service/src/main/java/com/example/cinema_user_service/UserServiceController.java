package com.example.cinema_user_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_user_service.dao.UserDAO;

import model.User;



@RestController
@RequestMapping("/api")
public class UserServiceController {    
    @PostMapping("/login")
    public User handleUserLogin(@RequestBody User user) {
    	return new UserDAO().login(user);
    }
    
    @GetMapping("/find-user-by-id")
    public User getUserById(@RequestParam int id) {
    	return new UserDAO().findById(id);
    }
    
    @PostMapping("/get-user-by-ids")
    public ArrayList<User> getUserListByIds(@RequestBody ArrayList<Integer> userIds) {
    	System.out.println("STARTING...");
    	return new UserDAO().getUserListByIds(userIds);
    }
    
}