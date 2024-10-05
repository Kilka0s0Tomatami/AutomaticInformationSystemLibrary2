package com.example.auto_information_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.auto_information_system.model.LibCards;

import com.example.auto_information_system.repo.LibCardsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibCardsService {
    @Autowired
    private LibCardsRepository libCardsRepository;
    
    public List<LibCards> getAllLibCards() {
        return libCardsRepository.findAll();
    }
    
    public void saveLibCard(LibCards card) {
        libCardsRepository.save(card);
    }

    public LibCards getLibCardById(Integer id) {
        return libCardsRepository.findById(id).orElse(null);
    }

    public void deleteLibCard(Integer id) {
        libCardsRepository.deleteById(id);
    }

    public Optional<LibCards> findByUserId(Integer UserId) {
        return libCardsRepository.findByUserId(UserId);
    }
    public void updateLibCard(LibCards card) {
        LibCards updCard = libCardsRepository.findById(card.getLib_card_id()).orElse(null);
        updCard.setLib_card_first_name(card.getLib_card_first_name());
        updCard.setLib_card_second_name(card.getLib_card_second_name());
        updCard.setLib_card_father_name(card.getLib_card_father_name());
        updCard.setLib_card_mobilephone_number(card.getLib_card_mobilephone_number());
        updCard.setLib_card_homephone_number(card.getLib_card_homephone_number());
        libCardsRepository.save(updCard);
    }
}
