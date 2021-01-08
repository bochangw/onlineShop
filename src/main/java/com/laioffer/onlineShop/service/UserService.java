package com.laioffer.onlineShop.service;

import com.laioffer.onlineShop.entity.User;
import com.laioffer.onlineShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getAUserByEmailId(String emailId) {
        for(User user: userRepository.findAll()) {
            if(user.getEmailId().equals(emailId)) {
                return user;
            }
        }
        return null;
    }

    public Boolean isUserCorrect(User user) {
        User loginUser = getAUserByEmailId(user.getEmailId());
        return loginUser != null;
    }
}
