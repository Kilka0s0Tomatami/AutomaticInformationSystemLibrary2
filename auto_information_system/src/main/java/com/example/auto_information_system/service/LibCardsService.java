package com.example.auto_information_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.auto_information_system.model.LibCards;

import com.example.auto_information_system.repo.LibCardsRepository;

import java.util.List;

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
}
