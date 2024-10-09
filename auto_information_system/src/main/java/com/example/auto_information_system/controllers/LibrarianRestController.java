package com.example.auto_information_system.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.service.LibrarianService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LibrarianRestController {
    @Autowired
    private LibrarianService librarianService;
    @PostMapping("/librarian/issueBook")
    public String postMethodName(@RequestParam Integer bookEditionId, @RequestParam Integer libCardId){ 
        try {
            librarianService.issueBookCopies(bookEditionId, libCardId);
            return "OK";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
    
}
