package com.example.auto_information_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "book_editions")
public class BookEditions {
    /*столбцы таблицы book_edition */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //вроде отдаёт управление генерацией бд / или автоинкрементн
    private int book_edition_id;

    @Column(nullable = false)    //означает, что поле не может быть пустым
    private String book_edition_title;

    @Column(nullable = false)
    private String book_edition_author;

    @Column(nullable = false)
    private int book_edition_year_publication;

    @Column(nullable = false)
    private String book_edition_place_publication;

    private String book_edition_udk_number;

    private String book_edition_bbk_number;

//конструкторы
    public BookEditions() {};
    public BookEditions(String book_edition_title, String book_edition_author,
                         int book_edition_year_publication, String book_edition_place_publication, 
                         String book_edition_udk_number, String book_edition_bbk_number) 
    {
        this.book_edition_title = book_edition_title;
        this.book_edition_author = book_edition_author;
        this.book_edition_year_publication = book_edition_year_publication;
        this.book_edition_place_publication = book_edition_place_publication;
        this.book_edition_udk_number = book_edition_udk_number;
        this.book_edition_bbk_number = book_edition_bbk_number;
    }


    //геттеры и сеттеры
    public int getBook_edition_id() {
        return book_edition_id;
    }
    public void setBook_edition_id(int book_edition_id) {
        this.book_edition_id = book_edition_id;
    }

    public String getBook_edition_title() {
        return book_edition_title;
    }
    public void setBook_edition_title(String book_edition_title) {
        this.book_edition_title = book_edition_title;
    }

    public String getBook_edition_author() {
        return book_edition_author;
    }
    public void setBook_edition_author(String book_edition_author) {
        this.book_edition_author = book_edition_author;
    }

    public int getBook_edition_year_publication() { 
        return book_edition_year_publication;
    }
    public void setBook_edition_year_publication(int book_edition_year_publication) {
        this.book_edition_year_publication = book_edition_year_publication;
    }

    public String getBook_edition_place_publication() {
        return book_edition_place_publication;
    }
    public void setBook_edition_place_publication(String book_edition_place_publication) {
        this.book_edition_place_publication = book_edition_place_publication;
    }

    public String getBook_edition_udk_number() {
        return book_edition_udk_number;
    }
    public void setBook_edition_udk_number(String book_edition_udk_number) {
        this.book_edition_udk_number = book_edition_udk_number;
    }

    public String getBook_edition_bbk_number() {
        return book_edition_bbk_number;
    }
    public void setBook_edition_bbk_number(String book_edition_bbk_number) {
        this.book_edition_bbk_number = book_edition_bbk_number;
    }
}
