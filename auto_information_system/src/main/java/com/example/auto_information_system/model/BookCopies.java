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

    @Column(nullable = false)
    private int book_edition_id;

    private int book_copy_status;

    @Column(nullable = false)
    private int book_copy_fond_number;


    //конструкторы
    public BookCopies() {
    }

    public BookCopies(int book_edition_id, int book_copy_status, int book_copy_fond_number) {
        this.book_edition_id = book_edition_id;
        this.book_copy_status = book_copy_status;
        this.book_copy_fond_number = book_copy_fond_number;
    }


    //геттеры и сеттеры
    public int getBook_copy_id() {
        return book_copy_id;
    }

    public void setBook_copy_id(int book_copy_id) {
        this.book_copy_id = book_copy_id;
    }

    public int getBook_edition_id() {
        return book_edition_id;
    }

    public void setBook_edition_id(int book_edition_id) {
        this.book_edition_id = book_edition_id;
    }   

    public int getBook_copy_status() {
        return book_copy_status;
    }

    public void setBook_copy_status(int book_copy_status) {
        this.book_copy_status = book_copy_status;
    }

    public int getBook_copy_fond_number() {
        return book_copy_fond_number;
    }

    public void setBook_copy_fond_number(int book_copy_fond_number) {
        this.book_copy_fond_number = book_copy_fond_number;
    }

}
