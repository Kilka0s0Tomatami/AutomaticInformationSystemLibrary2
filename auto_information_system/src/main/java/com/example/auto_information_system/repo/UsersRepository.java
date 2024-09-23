package com.example.auto_information_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auto_information_system.model.Users;

import java.util.Optional;
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
