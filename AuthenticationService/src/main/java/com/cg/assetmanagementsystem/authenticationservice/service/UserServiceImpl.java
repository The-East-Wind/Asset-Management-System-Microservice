package com.cg.assetmanagementsystem.authenticationservice.service;

import com.cg.assetmanagementsystem.authenticationservice.dao.UserDAO;
import com.cg.assetmanagementsystem.authenticationservice.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void addUsers() {
        List<ApplicationUser> applicationUserList = new ArrayList<>();
        applicationUserList.add(new ApplicationUser("heisenberg",bCryptPasswordEncoder.encode("saymyname")));
        applicationUserList.add(new ApplicationUser("jesse.pinkman",bCryptPasswordEncoder.encode("y0y0y0")));
        applicationUserList.add(new ApplicationUser("michael",bCryptPasswordEncoder.encode("kaylee")));
        applicationUserList.add(new ApplicationUser("nacho.varga",bCryptPasswordEncoder.encode("papi")));
        applicationUserList.add(new ApplicationUser("gus.fring",bCryptPasswordEncoder.encode("losPollos")));
        applicationUserList.add(new ApplicationUser("don.hector",bCryptPasswordEncoder.encode("cabaron")));
        applicationUserList.add(new ApplicationUser("kim.wexler",bCryptPasswordEncoder.encode("giselle")));
        applicationUserList.add(new ApplicationUser("lydia.quale",bCryptPasswordEncoder.encode("madrigal")));
        userDAO.saveAll(applicationUserList);
    }

    @Override
    public void signUpUser(ApplicationUser user) {
        user = userDAO.save(user);
        System.out.println(user);
    }
}
