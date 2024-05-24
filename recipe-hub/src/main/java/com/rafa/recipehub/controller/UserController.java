package com.rafa.recipehub.controller;

import com.rafa.recipehub.model.User;
import com.rafa.recipehub.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/api/users/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userServices.findUserByJwt(jwt);
        return user;
    }


}
