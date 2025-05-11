package com.example.todo_app_java.controller;

import com.example.todo_app_java.dto.LoginRequest;
import com.example.todo_app_java.dto.LoginResponse;
import com.example.todo_app_java.model.User;
import com.example.todo_app_java.repository.UserRepository;
import com.example.todo_app_java.service.UserService;
import com.example.todo_app_java.service.JwtService;
import com.example.todo_app_java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private  UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getUserId(), user.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("signup")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("username")
    public ResponseEntity<?> getUsernameFromToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        boolean isValid = jwtService.validateToken(token);

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        String username = jwtService.extractUsername(token);
        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        return ResponseEntity.ok(response);

    }

}
