package com.example.auto_information_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.BookCopies;
import com.example.auto_information_system.repo.BookCopiesRepository;

@Service
public class BookCopiesService {
    @Autowired
    private BookCopiesRepository bookCopiesRepository;
    
    public List<BookCopies> getAllBookCopies() {
        return bookCopiesRepository.findAll();
    }
    
    public void saveBookCopies(BookCopies book) {
        bookCopiesRepository.save(book);
    }

    public BookCopies getBookCopiesById(Integer id) {
        return bookCopiesRepository.findById(id).orElse(null);
    }

    public void deleteBookCopies(Integer id) {
        bookCopiesRepository.deleteById(id);
    }
}
