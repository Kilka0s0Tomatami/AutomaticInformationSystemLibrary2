package com.example.auto_information_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.auto_information_system.model.Stillages;
import com.example.auto_information_system.repo.StillagesRepository;

@Service
public class StillagesService {
    
    @Autowired
    private StillagesRepository stillagesRepository;
    
    public List<Stillages> getAllStillages() {
        return stillagesRepository.findAll();
    }
    
    public void saveStillage(Stillages card) {
        stillagesRepository.save(card);
    }

    public Stillages getStillageById(Integer id) {
        return stillagesRepository.findById(id).orElse(null);
    }

    public void deleteStillage(Integer id) {
        stillagesRepository.deleteById(id);
    }
}
