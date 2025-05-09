package com.example.todo_app_java.service;

import com.example.todo_app_java.model.User;
import com.example.todo_app_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User createUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("User Created Sucessfully");
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long userId){
        return userRepository.findById(userId);
    }
    public User getEmail(String email){
        return userRepository.findByEmail(email);
    }
}
