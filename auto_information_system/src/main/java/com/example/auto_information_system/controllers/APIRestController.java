package com.example.auto_information_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.model.BookEditions;
import com.example.auto_information_system.service.BookEditionService;

@RestController
public class APIRestController {
    @Autowired
    BookEditionService bookEditionService;
    

    @PostMapping("/api/AddBookEdition")
    public ResponseEntity<BookEditions> AddBookEdition(@RequestBody BookEditions entity) {
        bookEditionService.saveBookEdition(entity);
        
        return ResponseEntity.ok(entity);
    }
    @GetMapping("/api/findByBookEditionAuthor")
    public List<BookEditions> findByBookEditionAuthor(@RequestParam String author) {
        return bookEditionService.findByBookEditionAuthor(author);
    }

    @GetMapping("/api/searchBookEdition")
    public List<BookEditions> searchBookEdition(@RequestParam(required = false) String author, 
                                                @RequestParam(required = false) String title, 
                                                @RequestParam(required = false) Integer year,
                                                @RequestParam(required = false) String place,
                                                @RequestParam(required = false) String udk,
                                                @RequestParam(required = false) String bbk) {
        return bookEditionService.searchBooks(author, title, year, place, udk, bbk);
    }
}
