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
import com.example.auto_information_system.service.CustomUserDetails;
import com.example.auto_information_system.service.LibCardsService;
import com.example.auto_information_system.service.UsersService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collections;





@Controller
public class HelloController {
    
    @Autowired
    private UsersService usersService;
    @Autowired
    private LibCardsService libCardsService;

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            // Получаем кастомный объект пользователя
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Integer userId = userDetails.getId(); // Получаем ID пользователя

            // Логика для ролей
            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "administration.html";
            }
            else if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_LIBRARIAN"))) {
                return "clientWork.html";
            }
            else if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                if (libCardsService.findByUserId(userId).isPresent()) {
                    return "userHaveCardHomePage.html";
                }
                else
                return "userNoHaveCardHomePage.html";
            }
        }
        return "home.html";
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
    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
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
    @GetMapping("/images/logo1.png")
     public void logo1(HttpServletResponse response) {
         try { 
            InputStream fileInputStream= new FileInputStream("auto_information_system\\src\\main\\resources\\templates\\images\\logo1.png"); 
            response.setContentType(MediaType.IMAGE_JPEG_VALUE); 
            StreamUtils.copy(fileInputStream,response.getOutputStream()); 
        } catch(Exception e) { 
            e.printStackTrace(); 
        } 
    }

    
    @GetMapping("/css/login.css")
    public String loginCSS() {
        return "css/login.css";
    }
    @GetMapping("/register")
    public String registerPage() {
        return "register.html";
    }
    @GetMapping("/css/register.css")
    public String registerCSS() {
        return "css/register.css";
    }
    @GetMapping("/JavaScript/register.js")
    public String registerJS() {
        return "JavaScript/register.js";
    }
    @GetMapping("/css/userHaveCardHomePage.css")
    public String userHaveCardHomePageCSS() {
        return "css/userHaveCardHomePage.css";
    }
    @GetMapping("/JavaScript/userHaveCardHomePage.js")
    public String userHaveCardHomePageJS() {
        return "JavaScript/userHaveCardHomePage.js";
    }
    
   @GetMapping("/JavaScript/userNoHaveCardHomePage.js")
    public String userNoHaveCardHomePageJS() {
        return "JavaScript/userNoHaveCardHomePage.js";
    }
    
    @GetMapping("/css/librarianHomePage.css")
    public String librarianHomePageCSS() {
        return "css/librarianHomePage.css";
    }
    @GetMapping("/JavaScript/librarianHomePage.js")
    public String librarianHomePageJS() {
        return "JavaScript/librarianHomePage.js";
    }
}
    

