package com.example.auto_information_system.super_service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.LibCards;
import com.example.auto_information_system.service.BookCopiesService;
import com.example.auto_information_system.service.CustomUserDetails;
import com.example.auto_information_system.service.LibCardsService;

@Service
public class SuperUserService {
    @Autowired
    private LibCardsService libCardsService;
    @Autowired
    private BookCopiesService bookCopiesService;
    public Integer getUserIdByAuthentication(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getId(); // Получаем ID пользователя
        return userId;
    }
    public LibCards getUserLibCard(Authentication authentication) {
        return libCardsService.findByUserId(this.getUserIdByAuthentication(authentication)).get();
    }
    public List<Map<String, Object>> getUserBooksOnHands(Authentication authentication) {
        Integer userLibCardId = this.getUserLibCard(authentication).getLib_card_id();
        return bookCopiesService.getBooksOnHandsByLibCardId(userLibCardId);
    }
}
