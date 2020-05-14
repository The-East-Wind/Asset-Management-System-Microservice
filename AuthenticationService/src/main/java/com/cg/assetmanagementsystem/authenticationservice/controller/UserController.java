package com.cg.assetmanagementsystem.authenticationservice.controller;

import com.cg.assetmanagementsystem.authenticationservice.entity.ApplicationUser;
import com.cg.assetmanagementsystem.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserController(){
    }
    @PostMapping(value = "/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.signUpUser(user);
    }
}
