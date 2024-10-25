package com.example.auto_information_system.repo;


import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.auto_information_system.model.BookCopies;

import jakarta.transaction.Transactional;

@Repository
public interface BookCopiesRepository extends JpaRepository<BookCopies, Integer> {
    
    Optional<BookCopies> findFirstByBookEditionIdAndBookCopyStatus(Integer BookEditionId, Integer BookCopyStatus);

    @Transactional
    @Modifying
    @Query("UPDATE BookCopies b SET b.bookCopyStatus = :bookCopyStatus WHERE b.book_copy_id = :bookCopyId")
    void updateBookCopiesStatus(@Param("bookCopyStatus") int bookCopyStatus, @Param("bookCopyId") int bookCopyId);

    @Query("SELECT bc, boh FROM BookCopies bc JOIN BooksOnHands boh ON bc.book_copy_id = boh.book_copy_id " +
       "WHERE bc.bookEditionId = :bookEditionId AND boh.libCardId = :libCardId AND bc.bookCopyStatus = :bookCopyStatus")
    Optional<List<Object[]>> findBookByEditionCardAndStatus(@Param("bookEditionId") int bookEditionId,
                                                        @Param("libCardId") int libCardId,
                                                        @Param("bookCopyStatus") int bookCopyStatus);


    BookCopies findByBookCopyFondNumber(int bookCopyFondNumber);
}
