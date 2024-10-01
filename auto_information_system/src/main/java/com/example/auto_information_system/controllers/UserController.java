package com.example.auto_information_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.auto_information_system.service.BookCopiesService;
import com.example.auto_information_system.service.LibCardsService;
import com.example.auto_information_system.service.UsersService;
@Controller
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private LibCardsService libCardsService;
    @Autowired
    private BookCopiesService copiesService;

    @GetMapping("/reserveBook")
    public String reserveBook() {
        return "html/reserveBook.html";
    }
    @GetMapping("/receivedBooks")
    public String receivedBooks() {
        return "html/receivedBooks.html";
    }




    @GetMapping("/css/reserveBook.css")
    public String reserveBookCss() {
        return "css/reserveBook.css";
    }
    @GetMapping("/JavaScript/reserveBook.js")
    public String reserveBookJs() {
        return "JavaScript/reserveBook.js";
    }

    @GetMapping("/css/receivedBooks.css")
    public String receivedBooksCss() {
        return "css/receivedBooks.css";
    }
    @GetMapping("/JavaScript/receivedBooks.js")
    public String receivedBooksJs() {
        return "JavaScript/receivedBooks.js";
    }
}
