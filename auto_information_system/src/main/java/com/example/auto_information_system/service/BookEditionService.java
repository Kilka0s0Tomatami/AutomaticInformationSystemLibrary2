package com.example.auto_information_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.BookEdition;
import com.example.auto_information_system.repo.BookEditionRepository;

@Service
public class BookEditionService {
    @Autowired
    private BookEditionRepository bookEditionRepository;
    
    public List<BookEdition> getAllBookEdition() {
        return bookEditionRepository.findAll();
    }
    
    public void saveBookEdition(BookEdition book) {
        bookEditionRepository.save(book);
    }

    public BookEdition getBookEditionById(Integer id) {
        return bookEditionRepository.findById(id).orElse(null);
    }

    public void deleteBookEdition(Integer id) {
        bookEditionRepository.deleteById(id);
    }
}
