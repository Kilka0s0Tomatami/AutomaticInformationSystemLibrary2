package com.example.auto_information_system.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.model.LibCards;
import com.example.auto_information_system.service.BookCopiesService;

import com.example.auto_information_system.service.LibCardsService;
import com.example.auto_information_system.super_service.SuperUserService;


@RestController
public class UserRestController {
    @Autowired
    private LibCardsService libCardsService;
    @Autowired
    private BookCopiesService bookCopiesService;
    @Autowired
    private SuperUserService superUserService;

    @GetMapping("/user/getUserLibCard")
    public LibCards loginPage(Authentication authentication) {
        return superUserService.getUserLibCard(authentication);
    }

    @GetMapping("/user/getReceivedBooks")
    public List<Map<String, Object>> getReceivedBooks(Authentication authentication) {
        try {
            return superUserService.getUserBooksOnHands(authentication);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
    }


    @PostMapping("/user/updateUserLibCard")
    public ResponseEntity<HttpStatus> updateUserLibCard(@RequestBody LibCards entity) {
        try{
            libCardsService.updateLibCard(entity);
            return new ResponseEntity<>(HttpStatus.OK); 
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/user/registerUserLibCard")
    public ResponseEntity<HttpStatus> registerUserLibCard( Authentication authentication, @RequestBody LibCards entity) {
        try{
            entity.setUser_id(superUserService.getUserIdByAuthentication(authentication));
            libCardsService.saveLibCard(entity);
            return new ResponseEntity<>(HttpStatus.OK); 
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/user/reserveBooks/{id}")
    public ResponseEntity<HttpStatus> postMethodName(Authentication authentication, @PathVariable Integer id) {
        try {
            bookCopiesService.reserveBookCopies(id, superUserService.getUserLibCard(authentication).getLib_card_id());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
