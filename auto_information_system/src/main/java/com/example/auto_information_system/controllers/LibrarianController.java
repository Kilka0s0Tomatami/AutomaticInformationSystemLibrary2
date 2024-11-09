package com.example.auto_information_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibrarianController {
    @GetMapping("/clientWork")
    public String clientWork() {
        return "clientWork.html";
    }
    @GetMapping("/editionWork")
    public String editionWork() {
        return "editionWork.html";
    }
    @GetMapping("/copyWork")
    public String copyWork() {
        return "copyWork.html";
    }
}
