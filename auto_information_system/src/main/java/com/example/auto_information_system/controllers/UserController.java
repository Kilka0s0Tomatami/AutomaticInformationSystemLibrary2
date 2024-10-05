package com.example.auto_information_system.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


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
