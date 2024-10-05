package com.example.auto_information_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.auto_information_system.model.BooksOnHands;
import com.example.auto_information_system.repo.BooksOnHandsRepository;


import java.util.List;


@Service
public class BooksOnHandsService {
    @Autowired
    private BooksOnHandsRepository booksOnHandsRepository;


    public List<BooksOnHands> getBooksOnHandsByLibCardId(Integer libCardId) {
        return booksOnHandsRepository.findByLibCardId(libCardId);
    }


    public List<BooksOnHands> getAllBooksOnHands() {
        return booksOnHandsRepository.findAll();
    }
    
    public void saveBookOnHands(BooksOnHands book) {
        booksOnHandsRepository.save(book);
    }

    public BooksOnHands getBookOnHandsById(Integer id) {
        return booksOnHandsRepository.findById(id).orElse(null);
    }

    public void deleteBookOnHands(Integer id) {
        booksOnHandsRepository.deleteById(id);
    }
}
