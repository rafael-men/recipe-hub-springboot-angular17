package com.rafa.recipehub.controller;


import com.rafa.recipehub.model.User;
import com.rafa.recipehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {
        User isExist = findByEmail(user.getEmail());
        if(isExist != null ){
            throw new Exception("User is Exists");
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {

        userRepository.deleteById(userId);
        return "User Deleted";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() throws Exception {

        List<User> users = userRepository.findAll();
        return users;
    }

    public User findByEmail(String email) throws Exception{
        User user = userRepository.findByEmail(email);
        if(user == null ){
            throw new Exception("User not Found");
        }
        return user;
    }
}
