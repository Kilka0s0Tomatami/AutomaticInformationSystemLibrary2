package com.example.auto_information_system.controllers;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class HelloController {
    
    @GetMapping("/home")
    public String homePage() {
        return "html/home.html";
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
    
}
