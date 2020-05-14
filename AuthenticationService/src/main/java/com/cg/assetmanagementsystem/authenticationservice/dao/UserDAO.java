package com.cg.assetmanagementsystem.authenticationservice.dao;

import com.cg.assetmanagementsystem.authenticationservice.entity.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends CrudRepository<ApplicationUser,Integer> {
    Optional<ApplicationUser> findByUsername(String username);
}
