package com.example.auto_information_system.repo;



import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.auto_information_system.model.BooksOnHands;

import jakarta.transaction.Transactional;

@Repository
public interface BooksOnHandsRepository extends JpaRepository<BooksOnHands, Integer> {
    List<BooksOnHands> findByLibCardId(Integer libCardId);

    List<BooksOnHands> findByLibCardIdAndBookOnHandStatus(Integer libCardId , Integer BookOnHandStatus);
    
    
    @Query("SELECT e FROM BooksOnHands e WHERE e.returnDate < :currentDate AND e.bookOnHandStatus = 2")
    List<BooksOnHands> overdueRecords(java.sql.Date currentDate);
}
