package com.example.auto_information_system.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.model.LibCards;
import com.example.auto_information_system.service.BookCopiesService;
import com.example.auto_information_system.service.BooksOnHandsService;
import com.example.auto_information_system.service.CustomUserDetails;
import com.example.auto_information_system.service.LibCardsService;
import com.example.auto_information_system.service.UsersService;

@RestController
public class UserRestController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private LibCardsService libCardsService;
    @Autowired
    private BookCopiesService bookCopiesService;
    @Autowired
    private BooksOnHandsService booksOnHandsService;

    @GetMapping("/user/getUserLibCard")
    public LibCards loginPage(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId(); // Получаем ID пользователя
        return libCardsService.findByUserId(userId).get();
    }

    @GetMapping("/user/getReceivedBooks")
    public List<Map<String, Object>> getReceivedBooks(Authentication authentication) {
        try {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId();
        Integer userLibCardId = libCardsService.findByUserId(userId).get().getLib_card_id();
        return bookCopiesService.getBooksOnHandsByLibCardId(userLibCardId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
    }





    @PostMapping("/user/reserveBooks/{id}")
    public ResponseEntity<HttpStatus> postMethodName(Authentication authentication, @PathVariable Integer id) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId();
        try {
            bookCopiesService.reserveBookCopies(id, libCardsService.findByUserId(userId).get().getLib_card_id());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
