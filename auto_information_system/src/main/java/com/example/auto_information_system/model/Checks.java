package com.example.auto_information_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "checks")
public class Checks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int check_id;

    @Column(nullable = false)
    private int lib_card_id;

    @Column(nullable = false)
    private int book_copy_id;

    @Column(nullable = false)
    private java.sql.Date check_start_date;
    
    private java.sql.Date check_finish_date;

    public Checks() {
    }

    public Checks(int lib_card_id, int book_copy_id, java.sql.Date check_start_date, java.sql.Date check_finish_date) {
        this.lib_card_id = lib_card_id;
        this.book_copy_id = book_copy_id;
        this.check_start_date = check_start_date;
        this.check_finish_date = check_finish_date;
    }

    public int getCheck_id() {
        return check_id;
    }

    public void setCheck_id(int check_id) {
        this.check_id = check_id;
    }

    public int getLib_card_id() {
        return lib_card_id;
    }

    public void setLib_card_id(int lib_card_id) {
        this.lib_card_id = lib_card_id;
    }

    public int getBook_copy_id() {
        return book_copy_id;
    }

    public void setBook_copy_id(int book_copy_id) {
        this.book_copy_id = book_copy_id;
    }

    public java.sql.Date getCheck_start_date() {
        return check_start_date;
    }

    public void setCheck_start_date(java.sql.Date check_start_date) {
        this.check_start_date = check_start_date;
    }

    public java.sql.Date getCheck_finish_date() {
        return check_finish_date;
    }

    public void setCheck_finish_date(java.sql.Date check_finish_date) {
        this.check_finish_date = check_finish_date;
    }
    
}
