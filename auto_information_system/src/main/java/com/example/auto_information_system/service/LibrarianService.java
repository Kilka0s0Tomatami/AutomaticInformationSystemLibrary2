package com.example.auto_information_system.service;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.BookCopies;
import com.example.auto_information_system.model.BooksOnHands;
import com.example.auto_information_system.repo.BookCopiesRepository;

@Service
public class LibrarianService {
    
    @Autowired
    private BookCopiesRepository bookCopiesRepository;
    @Autowired
    private BooksOnHandsService booksOnHandsService;
    @Autowired
    private PlacedBookCopiesService placedBookCopiesService;
    

    public void issueBookCopies(Integer EditionId, Integer libCardId) {
        //находим копию по изданию и статусу расположена(1)


        Optional<Object[]> results = bookCopiesRepository.findBookByEditionCardAndStatus(EditionId, libCardId, 2);
        if (!results.isEmpty()) {
          
            BookCopies book = (BookCopies)results.get()[0];
            bookCopiesRepository.updateBookCopiesStatus(3, book.getBook_copy_id()); // обновление статуса копии
            
            BooksOnHands booksOnHands = (BooksOnHands)results.get()[1];
            booksOnHands.setBookOnHandStatus(2);
            booksOnHandsService.saveBookOnHands(booksOnHands); //добавление в таблицу books_on_hands

            placedBookCopiesService.deletePlacedBookCopies(book.getBook_copy_id());
            return;
        }

        BookCopies book = bookCopiesRepository.findFirstByBookEditionIdAndBookCopyStatus(EditionId, 1).orElse(null);
        if (book != null) {
            bookCopiesRepository.updateBookCopiesStatus(3, book.getBook_copy_id()); // обновление статуса копии
            
            Date currentDate = new Date(System.currentTimeMillis());// текущая дата
            LocalDate localDate = currentDate.toLocalDate(); // Преобразуем java.sql.Date в LocalDate
            LocalDate newDate = localDate.plusMonths(1);// Добавляем 1 месяц
            Date updatedDate = Date.valueOf(newDate);// Преобразуем LocalDate обратно в java.sql.Date

            BooksOnHands bookOnHands = new BooksOnHands(book.getBook_copy_id(), libCardId, currentDate, updatedDate, null, 2);
            booksOnHandsService.saveBookOnHands(bookOnHands); //добавление в таблицу books_on_hands
            return;
        }
        throw new RuntimeException("Book not found");      
    }
  //  public List<Map<String, Object>> getBooksOnHandsByLibCardId(Integer userLibCardId) {
      //  return booksOnHandsService.getBooksOnHandsByLibCardId(userLibCardId);
   // }
}
