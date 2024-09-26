package com.example.auto_information_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.model.BookCopies;
import com.example.auto_information_system.service.BookCopiesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class BookCopiesController {
    @Autowired
    BookCopiesService bookCopiesService;

    @GetMapping("/api/getAllBookCopies")
    public List<BookCopies> getAllBookCopies() {
        return bookCopiesService.getAllBookCopies();
    }
    @PostMapping("/api/addBookCopies")
    public ResponseEntity<BookCopies> postMethodName(@RequestBody BookCopies entity) {
        bookCopiesService.saveBookCopies(entity);
        
        return ResponseEntity.ok(entity);
    }
    
}
