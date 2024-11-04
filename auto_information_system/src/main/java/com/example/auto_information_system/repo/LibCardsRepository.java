package com.example.auto_information_system.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.auto_information_system.model.LibCards;


@Repository
public interface LibCardsRepository  extends JpaRepository<LibCards, Integer>  {
    Optional<LibCards> findByUserId(Integer UserId);
}
