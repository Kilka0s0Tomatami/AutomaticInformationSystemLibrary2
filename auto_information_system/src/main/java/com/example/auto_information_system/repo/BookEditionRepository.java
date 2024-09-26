package com.example.auto_information_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.auto_information_system.model.BookEditions;

@Repository
public interface BookEditionRepository extends JpaRepository<BookEditions, Integer> {
    
    @Query("SELECT b FROM BookEditions b WHERE b.book_edition_author = :author")
    public List<BookEditions> findByBookEditionAuthor(@Param("author") String bookEditionAuthor);

    @Query("SELECT b FROM BookEditions b WHERE " +
       "(COALESCE(:author, '') = '' OR b.book_edition_author = :author) AND " +
       "(COALESCE(:title, '') = '' OR b.book_edition_title = :title) AND " +
       "(COALESCE(:year, 0) = 0 OR b.book_edition_year_publication = :year) AND" +
       "(COALESCE(:place, '') = '' OR b.book_edition_place_publication = :place) AND" +
       "(COALESCE(:udk, '') = '' OR b.book_edition_udk_number = :udk) AND" +
       "(COALESCE(:bbk, '') = '' OR b.book_edition_bbk_number = :bbk)"
       )
    public List<BookEditions> searchBooks(@Param("author") String author,
                                        @Param("title") String title,
                                        @Param("year") Integer year,
                                        @Param("place") String place,
                                        @Param("udk") String udk,
                                        @Param("bbk") String bbk);
}
