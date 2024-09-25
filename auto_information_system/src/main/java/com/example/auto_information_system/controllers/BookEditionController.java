package com.example.auto_information_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.auto_information_system.service.BookEditionService;

@Controller
public class BookEditionController {
    @Autowired
    BookEditionService bookEditionService;
    
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            // Если пользователь имеет роль администратора
            return "html/admin-dashboard.html";
        } else {
            // Если обычный пользователь
            return "html/user-dashboard.html";
        }
    }
}
