package com.cg.assetmanagementsystem.authenticationservice.service;

import com.cg.assetmanagementsystem.authenticationservice.entity.ApplicationUser;

public interface UserService {
    void addUsers();
    void signUpUser(ApplicationUser user);
}
