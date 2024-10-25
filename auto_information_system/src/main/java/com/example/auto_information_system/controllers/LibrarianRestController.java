package com.example.auto_information_system.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.model.BookCopies;
import com.example.auto_information_system.model.BookEditions;
import com.example.auto_information_system.service.BookCopiesService;
import com.example.auto_information_system.service.BookEditionService;
import com.example.auto_information_system.service.LibrarianService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LibrarianRestController {
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private BookEditionService bookEditionService;
    @Autowired
    private BookCopiesService bookCopiesService;
    @PostMapping("/librarian/issueBook")
    public Map<String, Object> issueBook(@RequestParam Integer bookEditionId, @RequestParam Integer libCardId){ 
        try {
            
            return librarianService.issueBookCopies(bookEditionId, libCardId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Map<String, Object> entity1 = new HashMap<>();
            entity1.put("cell_id", "0");
            return entity1;
        }
    }
    
    @PostMapping("/librarian/returnBookCopies")
    public ResponseEntity<HttpStatus> returnBookCopies(@RequestParam Integer bookCopyFondNumber){ 
        try {
            librarianService.returnBookCopies(bookCopyFondNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/librarian/arrangeBookCopies")
    public ResponseEntity<HttpStatus> arrangeBookCopies(@RequestParam Integer bookCopyFondNumber, @RequestParam Integer cellId){
        try {
            librarianService.arrangeBookCopies(bookCopyFondNumber, cellId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/librarian/addBookEdition")
    public ResponseEntity<HttpStatus> addBookEdition(@RequestBody BookEditions entity){
        try {
            bookEditionService.saveBookEdition(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/librarian/addBookCopies")
    public ResponseEntity<HttpStatus> addBookCopies(@RequestBody BookCopies entity){
        try {
            bookCopiesService.saveBookCopies(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
