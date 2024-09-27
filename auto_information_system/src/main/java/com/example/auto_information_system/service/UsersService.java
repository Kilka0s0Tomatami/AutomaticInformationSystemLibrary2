package com.example.auto_information_system.service;

import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.Users;
import com.example.auto_information_system.repo.UsersRepository;




@Service
public class UsersService implements UserDetailsService {
   @Autowired
   private UsersRepository usersRepository;
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username).orElse(null);;
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        SimpleGrantedAuthority authority;
        if (user.getRole_id() == 2) {
            authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        } 
        else if (user.getRole_id() == 1) {
            authority = new SimpleGrantedAuthority("ROLE_USER");
        }
        else{
            authority = new SimpleGrantedAuthority("ROLE_LIBRARIAN");
        }

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getUser_password(),
            Collections.singletonList(authority)
        );
    }
    public void addUser(Users user) {
        if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already taken");
        } 
        else{
            user.setRole_id(1);
            usersRepository.save(user);  
        } 
        
    }
}
