package com.example.auto_information_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibrarianController {
    @GetMapping("/issueReceivedBook")
    public String loginPage() {
        return "issueReceivedBook.html";
    }
}
