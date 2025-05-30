package com.springproject.springsecurity.controller;

import com.springproject.springsecurity.entity.User;
import com.springproject.springsecurity.repository.UserRepository;
import com.springproject.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User setUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> existUser = userRepository.findByUsername(user.getUsername());
        if (!existUser.isEmpty()) {
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Aviabale", HttpStatus.NOT_FOUND);
    }
}
