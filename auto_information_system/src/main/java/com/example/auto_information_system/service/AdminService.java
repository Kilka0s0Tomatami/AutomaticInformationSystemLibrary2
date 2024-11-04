package com.example.auto_information_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.Users;
import com.example.auto_information_system.repo.UsersRepository;

@Service
public class AdminService {
    @Autowired
    UsersRepository usersRepository;

    public void addNewUser(Users user) {
        if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already taken");
        } else {
            
            usersRepository.save(user);  
        }
    }
    public void updateUser(Users user) {
        usersRepository.save(user);     
    }
    public List<Users> getAllUsers() 
    {
        return usersRepository.findAll();
    }

}
