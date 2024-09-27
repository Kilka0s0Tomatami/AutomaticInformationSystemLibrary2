package com.example.auto_information_system.repo;



import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.auto_information_system.model.BooksOnHands;

@Repository
public interface BooksOnHandsRepository extends JpaRepository<BooksOnHands, Integer> {
    
}
