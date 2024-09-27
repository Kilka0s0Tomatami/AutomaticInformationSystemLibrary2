package com.example.auto_information_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auto_information_system.model.PlacedBookCopies;

@Repository
public interface PlacedBookCopiesRepository extends JpaRepository<PlacedBookCopies, Integer> {
    
}
