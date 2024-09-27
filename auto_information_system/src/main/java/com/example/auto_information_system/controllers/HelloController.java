package com.example.auto_information_system.controllers;

import java.io.FileInputStream;
import java.io.InputStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.auto_information_system.model.Users;
import com.example.auto_information_system.service.UsersService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collections;



@Controller
public class HelloController {
    
    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public String dashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            // Если пользователь имеет роль администратора
            return "html/admin-dashboard.html";
        }
        else if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_LIBRARIAN"))) {
            return "html/user-dashboard.html";
        } 
        else if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            return "html/user-dashboard.html";
        } 
        else {
            // Если обычный пользователь
             return "html/home.html";
        }
    }


   
    @GetMapping("/css/home.css")
    public String homeCss() {
        return "css/home.css";
    }
    @GetMapping("/JavaScript/home.js")
    public String homeJs() {
        return "JavaScript/home.js";
    }
    @GetMapping("/images/logo.png")
    public void logo(HttpServletResponse response) {
         try { 
            InputStream fileInputStream= new FileInputStream("auto_information_system\\src\\main\\resources\\templates\\images\\logo.png"); 
            response.setContentType(MediaType.IMAGE_JPEG_VALUE); 
            StreamUtils.copy(fileInputStream,response.getOutputStream()); 
        } catch(Exception e) { 
            e.printStackTrace(); 
        } 
    }

    @GetMapping("/login")
    public String loginPage() {
        return "html/login.html";
    }
    @GetMapping("/css/login.css")
    public String loginCSS() {
        return "css/login.css";
    }
    @GetMapping("/register")
    public String registerPage() {
        return "html/register.html";
    }
    @GetMapping("/css/register.css")
    public String registerCSS() {
        return "css/register.css";
    }
    @GetMapping("/JavaScript/register.js")
    public String registerJS() {
        return "JavaScript/register.js";
    }
    @PostMapping("/register")
    public ResponseEntity<?> postMethodName(@RequestBody Users entity) {
        try {
            usersService.addUser(entity);
            return ResponseEntity.ok(Collections.singletonMap("message", "Пользователь успешно зарегистрирован!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при регистрации");
        }
    }
}
    

