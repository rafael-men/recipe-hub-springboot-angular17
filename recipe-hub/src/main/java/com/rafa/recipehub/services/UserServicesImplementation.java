package com.rafa.recipehub.services;

import com.rafa.recipehub.config.JwtProvider;
import com.rafa.recipehub.model.User;
import com.rafa.recipehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImplementation implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    public UserServicesImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("User not found");
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailfromJwtToken(jwt);
        if(email == null) {
            throw new Exception("Provide Valid Jwt Token");
        }
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("User not found with email " + email);
        }
        return user;
    }
}
