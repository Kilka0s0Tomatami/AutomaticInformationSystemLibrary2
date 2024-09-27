package com.example.auto_information_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.model.LibCards;
import com.example.auto_information_system.service.CustomUserDetails;
import com.example.auto_information_system.service.LibCardsService;
import com.example.auto_information_system.service.UsersService;

@RestController
public class UserRestController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private LibCardsService libCardsService;

    @GetMapping("/user/getUserLibCard")
    public LibCards loginPage(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId(); // Получаем ID пользователя
        return libCardsService.findByUserId(userId).get();
    }
   
}
