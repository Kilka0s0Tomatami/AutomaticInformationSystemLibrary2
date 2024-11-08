package com.example.auto_information_system.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.auto_information_system.model.BookCopies;
import com.example.auto_information_system.model.BookEditions;
import com.example.auto_information_system.model.LibCards;
import com.example.auto_information_system.service.BookCopiesService;
import com.example.auto_information_system.service.BookEditionService;
import com.example.auto_information_system.service.LibCardsService;
import com.example.auto_information_system.service.LibrarianService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class LibrarianRestController {
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private BookEditionService bookEditionService;
    @Autowired
    private BookCopiesService bookCopiesService;
    @Autowired
    private LibCardsService libCardsService;
    @PostMapping("/librarian/issueBook")
    public Map<String, Object> issueBook(@RequestParam Integer bookEditionId, @RequestParam Integer libCardId){ 
        try {
            System.out.println("Errorы ");
            return librarianService.issueBookCopies(bookEditionId, libCardId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Map<String, Object> entity1 = new HashMap<>();
            entity1.put("cell_id", "0");
            return entity1;
        }
    }
    @PostMapping("/librarian/registerUserLibCard")
    public ResponseEntity<HttpStatus> registerUserLibCard(@RequestBody LibCards entity) {
        try{
            System.out.println(entity.getUserId());
            System.out.println(entity.getLib_card_second_name());
            System.out.println(entity.getLib_card_homephone_number());
            libCardsService.saveLibCard(entity);
            return new ResponseEntity<>(HttpStatus.OK); 
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    @GetMapping("/librarian/getBooksOnHandsByLibCardId")
    public List<Map<String, Object>> getBooksOnHandsByLibCardId(@RequestParam int libCardId) {
        return librarianService.getBooksOnHandsByLibCardId(libCardId);
    }
    @GetMapping("/librarian/getNotLocatedBooks")
    public List<BookCopies> getNotLocatedBooks() {
        return librarianService.getBooksByBookCopyStatus(0);
    }

    @GetMapping("/librarian/getBooksByEditionId")
    public List<BookCopies> getBooksByEditionId(@RequestParam int bookEditionId) {
        return librarianService.getBooksByBookEdition(bookEditionId);
    }
    
}
