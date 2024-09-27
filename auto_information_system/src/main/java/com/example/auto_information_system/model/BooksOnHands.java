package com.example.auto_information_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books_on_hands")
public class BooksOnHands {
    @Id
    private int book_copy_id;
    @Column(nullable = false)
    private int lib_card_id;
    @Column(nullable = false)
    private java.sql.Date issue_date;
    @Column(nullable = false)
    private java.sql.Date return_date;
    private java.sql.Date refund_date;

    public BooksOnHands() {
    }

    public BooksOnHands(int book_copy_id, int lib_card_id, java.sql.Date issue_date, java.sql.Date return_date, java.sql.Date refund_date) {
        this.book_copy_id = book_copy_id;
        this.lib_card_id = lib_card_id;
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.refund_date = refund_date;
    }

    public int getBook_copy_id() {
        return book_copy_id;
    }

    public void setBook_copy_id(int book_copy_id) {
        this.book_copy_id = book_copy_id;
    }

    public int getLib_card_id() {
        return lib_card_id;
    }

    public void setLib_card_id(int lib_card_id) {
        this.lib_card_id = lib_card_id;
    }

    public java.sql.Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(java.sql.Date issue_date) {
        this.issue_date = issue_date;
    }

    public java.sql.Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(java.sql.Date return_date) {
        this.return_date = return_date;
    }

    public java.sql.Date getRefund_date() {
        return refund_date;
    }

    public void setRefund_date(java.sql.Date refund_date) {
        this.refund_date = refund_date;
    }
}
