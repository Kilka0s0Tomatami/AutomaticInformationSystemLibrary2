package com.example.auto_information_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.BookEditions;
import com.example.auto_information_system.repo.BookEditionRepository;

@Service
public class BookEditionService {
    @Autowired
    private BookEditionRepository bookEditionRepository;
    
    public List<BookEditions> getAllBookEditions() {
        return bookEditionRepository.findAll();
    }
    
    public void saveBookEdition(BookEditions book) {
        bookEditionRepository.save(book);
    }

    public BookEditions getBookEditionById(Integer id) {
        return bookEditionRepository.findById(id).orElse(null);
    }

    public void deleteBookEdition(Integer id) {
        bookEditionRepository.deleteById(id);
    }

    public List<BookEditions> findByBookEditionAuthor(String bookEditionAuthor) {
        return bookEditionRepository.findByBookEditionAuthor(bookEditionAuthor);
    }

    public List<BookEditions> searchBooks(String author, String title, Integer year, String place, String udk, String bbk) {
        return bookEditionRepository.searchBooks(author, title, year, place, udk, bbk);
    }


}
