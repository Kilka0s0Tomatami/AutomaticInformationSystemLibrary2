package com.example.auto_information_system.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_copies")
public class BookCopies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_copy_id;

    @Column(nullable = false, name = "book_edition_id")
    private int bookEditionId;

    @Column(name = "book_copy_status")
    private int bookCopyStatus;

    @Column(nullable = false, name = "book_copy_fond_number")
    private int bookCopyFondNumber;


    //конструкторы
    public BookCopies() {
    }

    public BookCopies(int book_edition_id, int book_copy_status, int book_copy_fond_number) {
        this.bookEditionId = book_edition_id;
        this.bookCopyStatus = book_copy_status;
        this.bookCopyFondNumber = book_copy_fond_number;
    }


    //геттеры и сеттеры
    public int getBook_copy_id() {
        return book_copy_id;
    }

    public void setBook_copy_id(int book_copy_id) {
        this.book_copy_id = book_copy_id;
    }

    public int getBookEditionId() {
        return bookEditionId;
    }

    public void setBookEditionId(int book_edition_id) {
        this.bookEditionId = book_edition_id;
    }   

    public int getBookCopyStatus() {
        return bookCopyStatus;
    }

    public void setBookCopyStatus(int book_copy_status) {
        this.bookCopyStatus = book_copy_status;
    }

    public int getBookCopyFondNumber() {
        return bookCopyFondNumber;
    }

    public void setBookCopyFondNumber(int book_copy_fond_number) {
        this.bookCopyFondNumber = book_copy_fond_number;
    }

}
