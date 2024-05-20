package com.rafa.recipehub.services;


import com.rafa.recipehub.model.User;
import com.rafa.recipehub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImplementation implements UserServices {

    private UserRepository userRepository;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);
        if(opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("User not Found");
    }
}
