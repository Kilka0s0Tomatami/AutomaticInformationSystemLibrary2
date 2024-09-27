package com.example.auto_information_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auto_information_system.model.Checks;

@Repository
public interface ChecksRepository extends JpaRepository<Checks, Integer> {
    
}
