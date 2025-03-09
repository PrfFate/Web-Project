package com.aliacar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliacar.entities.User;
import com.aliacar.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User getOneUser(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User newUser){
        return userRepository.save(newUser);
    }

    public User updateOneUser(Long userId,User newUser){
         Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser=user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());

            return foundUser;
        }
        return null;
    }

    public void deleteById(Long userId){
         userRepository.deleteById(userId);
    }


}
