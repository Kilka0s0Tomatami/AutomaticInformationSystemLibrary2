package com.example.auto_information_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/administration")
    public String administration() {
        return "administration.html";
    }
    @GetMapping("/workWithCopy")
    public String workWithCopy() {
        return "workWithCopy.html";
    }
    @GetMapping("/workWithClient")
    public String workWithClient() {
        return "workWithClient.html";
    }
    @GetMapping("/workWithEditions")
    public String workWithEditions() {
        return "workWithEditions.html";
    }
}
