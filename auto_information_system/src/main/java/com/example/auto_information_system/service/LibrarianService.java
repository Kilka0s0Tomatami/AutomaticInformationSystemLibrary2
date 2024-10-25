package com.example.auto_information_system.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.BookCopies;
import com.example.auto_information_system.model.BooksOnHands;
import com.example.auto_information_system.model.PlacedBookCopies;
import com.example.auto_information_system.repo.BookCopiesRepository;

@Service
public class LibrarianService {
    
    @Autowired
    private BookCopiesRepository bookCopiesRepository;
    @Autowired
    private BooksOnHandsService booksOnHandsService;
    @Autowired
    private PlacedBookCopiesService placedBookCopiesService;
    @Autowired
    private BookCopiesService bookCopiesService;

    public Map<String, Object> issueBookCopies(Integer EditionId, Integer libCardId) {
        
        //находим забронированную книгу(2)
        Optional<List<Object[]>> results = bookCopiesRepository.findBookByEditionCardAndStatus(EditionId, libCardId, 2);
        List<Object[]> resultList = results.get();
    
        if (!resultList.isEmpty()) {
            System.out.println("Optional не пуст, продолжаем обработку.");
            for (Object[] row : results.get()) {
                BookCopies bookCopies = (BookCopies) row[0]; 
                BooksOnHands booksOnHands = (BooksOnHands) row[1]; 
            
                bookCopiesRepository.updateBookCopiesStatus(3, bookCopies.getBook_copy_id());
                booksOnHands.setBookOnHandStatus(2);
                booksOnHandsService.saveBookOnHands(booksOnHands);

                Map<String, Object> entity1 = new HashMap<>();
                entity1.put("cell_id", placedBookCopiesService.GetCellIdByBookCopyId(bookCopies.getBook_copy_id()));
                placedBookCopiesService.deletePlacedBookCopies(bookCopies.getBook_copy_id());
                    
                return entity1;
            }
        } else {
            System.out.println("книга не забронирована");
            //находим копию по изданию и статусу расположена(1)
            BookCopies book = bookCopiesRepository.findFirstByBookEditionIdAndBookCopyStatus(EditionId, 1).orElse(null);
            if (book != null) {
                bookCopiesRepository.updateBookCopiesStatus(3, book.getBook_copy_id()); // обновление статуса копии
                
                Date currentDate = new Date(System.currentTimeMillis());// текущая дата
                LocalDate localDate = currentDate.toLocalDate(); // Преобразуем java.sql.Date в LocalDate
                LocalDate newDate = localDate.plusMonths(1);// Добавляем 1 месяц
                Date updatedDate = Date.valueOf(newDate);// Преобразуем LocalDate обратно в java.sql.Date

                BooksOnHands bookOnHands = new BooksOnHands(book.getBook_copy_id(), libCardId, currentDate, updatedDate, null, 2);
                booksOnHandsService.saveBookOnHands(bookOnHands); //добавление в таблицу books_on_hands

                Map<String, Object> entity1 = new HashMap<>();
                entity1.put("cell_id", placedBookCopiesService.GetCellIdByBookCopyId(book.getBook_copy_id()));
                placedBookCopiesService.deletePlacedBookCopies(book.getBook_copy_id());

                return entity1;
            }
            
        } 
        throw new RuntimeException("Book not found");         
    }

    public List<Map<String, Object>> getBooksOnHandsByLibCardId(Integer userLibCardId) {
        return bookCopiesService.getBooksOnHandsByLibCardId(userLibCardId);
    }
  
    public void returnBookCopies(Integer bookCopyFondNumber) {
        BookCopies bookCopies = bookCopiesRepository.findByBookCopyFondNumber(bookCopyFondNumber);
        bookCopiesRepository.updateBookCopiesStatus(0, bookCopies.getBook_copy_id());
        booksOnHandsService.deleteBookOnHands(bookCopies.getBook_copy_id());
    }
    public void arrangeBookCopies(Integer bookCopyFondNumber, int cellId) {
        BookCopies bookCopies = bookCopiesRepository.findByBookCopyFondNumber(bookCopyFondNumber);
        bookCopiesRepository.updateBookCopiesStatus(1, bookCopies.getBook_copy_id());
        placedBookCopiesService.savePlacedBookCopies(new PlacedBookCopies(bookCopies.getBook_copy_id(), cellId));
    }
}
