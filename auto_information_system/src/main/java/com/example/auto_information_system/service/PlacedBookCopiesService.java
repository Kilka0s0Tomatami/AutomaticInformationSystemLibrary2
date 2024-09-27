package com.example.auto_information_system.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.PlacedBookCopies;
import com.example.auto_information_system.repo.PlacedBookCopiesRepository;


@Service
public class PlacedBookCopiesService {
    
    @Autowired
    private PlacedBookCopiesRepository placedBookCopiesRepository;
    
    public List<PlacedBookCopies> getAllPlacedBookCopies() {
        return placedBookCopiesRepository.findAll();
    }
    
    public void savePlacedBookCopies(PlacedBookCopies card) {
        placedBookCopiesRepository.save(card);
    }

    public PlacedBookCopies getPlacedBookCopiesById(Integer id) {
        return placedBookCopiesRepository.findById(id).orElse(null);
    }

    public void deletePlacedBookCopies(Integer id) {
        placedBookCopiesRepository.deleteById(id);
    }
}
