package com.rafa.recipehub.services;

import com.rafa.recipehub.model.User;

public interface UserServices {
    public User findUserById(Long userId) throws Exception;
    public User findUserByJwt(String Jwt) throws Exception;
}
