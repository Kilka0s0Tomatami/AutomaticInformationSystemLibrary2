package com.example.auto_information_system.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auto_information_system.model.BookCopies;

@Repository
public interface BookCopiesRepository extends JpaRepository<BookCopies, Integer> {
    
}
