package com.example.auto_information_system.service;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.Checks;
import com.example.auto_information_system.repo.ChecksRepository;


@Service
public class ChecksService {
    
    @Autowired
    private ChecksRepository checksRepository;
    
    public List<Checks> getAllChecks() {
        return checksRepository.findAll();
    }
    
    public void saveChecks(Checks card) {
        checksRepository.save(card);
    }

    public Checks getChecksById(Integer id) {
        return checksRepository.findById(id).orElse(null);
    }

    public void deleteChecks(Integer id) {
        checksRepository.deleteById(id);
    }
}
