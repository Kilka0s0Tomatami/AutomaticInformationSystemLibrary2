package com.example.auto_information_system.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.BookCopies;
import com.example.auto_information_system.model.BookEditions;
import com.example.auto_information_system.model.BooksOnHands;
import com.example.auto_information_system.repo.BookCopiesRepository;

@Service
public class BookCopiesService {
    @Autowired
    private BookCopiesRepository bookCopiesRepository;
    @Autowired
    private BooksOnHandsService booksOnHandsService;
    @Autowired
    private PlacedBookCopiesService placedBookCopiesService;
    @Autowired
    private BookEditionService bookEditionService;
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

    public void reserveBookCopies(Integer id, Integer libCardId) {
        //находим копию по изданию и статусу расположена(1)
        BookCopies book = bookCopiesRepository.findFirstByBookEditionIdAndBookCopyStatus(id, 1).orElse(null);
        if (book != null) {
            bookCopiesRepository.updateBookCopiesStatus(2, book.getBook_copy_id()); // обновление статуса копии
            
            Date currentDate = new Date(System.currentTimeMillis());// текущая дата
            LocalDate localDate = currentDate.toLocalDate(); // Преобразуем java.sql.Date в LocalDate
            LocalDate newDate = localDate.plusMonths(1);// Добавляем 1 месяц
            Date updatedDate = Date.valueOf(newDate);// Преобразуем LocalDate обратно в java.sql.Date

            BooksOnHands bookOnHands = new BooksOnHands(book.getBook_copy_id(), libCardId, currentDate, updatedDate, null);
            booksOnHandsService.saveBookOnHands(bookOnHands); //добавление в таблицу books_on_hands
            
            placedBookCopiesService.deletePlacedBookCopies(book.getBook_copy_id()); //удаление из таблицы placed_book_copies
        }
        else {
            throw new RuntimeException("Book not found"); 
        } 
    }


    public List<Map<String, Object>> getBooksOnHandsByLibCardId(Integer userLibCardId) {
        List<Map<String, Object>> jsonResponse = new ArrayList<>();
        List<BooksOnHands> booksOnHands = booksOnHandsService.getBooksOnHandsByLibCardId(userLibCardId);
        for (BooksOnHands bookOnHands : booksOnHands) {
            BookCopies bookCopies = this.getBookCopiesById(bookOnHands.getBook_copy_id());
            BookEditions bookEdition = bookEditionService.getBookEditionById(bookCopies.getBookEditionId());
            String status = null;
            if (bookCopies.getBookCopyStatus()==2) {
                status = "Забронирована";
            }
            else if (bookCopies.getBookCopyStatus()==3) {
                status = "Взята";
            }
            else if (bookCopies.getBookCopyStatus()==4) {
                status = "Возвращена";
            }
            Map<String, Object> entity1 = new HashMap<>();
            entity1.put("author", bookEdition.getBook_edition_author());
            entity1.put("title", bookEdition.getBook_edition_title());
            entity1.put("year_publication", bookEdition.getBook_edition_year_publication());
            entity1.put("status", status);
            entity1.put("issueDate", bookOnHands.getIssue_date());
            entity1.put("returnDate", bookOnHands.getReturn_date());
            jsonResponse.add(entity1);
        }
        return jsonResponse;
    }
}
