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
    @Column(nullable = false, name = "lib_card_id")
    private int libCardId;
    @Column(nullable = false)
    private java.sql.Date issue_date;
    @Column(nullable = false)
    private java.sql.Date return_date;
    private java.sql.Date refund_date;
    @Column(nullable = false, name = "book_on_hand_status")
    private int bookOnHandStatus;

    public BooksOnHands() {
    }

    public BooksOnHands(int book_copy_id, int lib_card_id, java.sql.Date issue_date, java.sql.Date return_date, java.sql.Date refund_date, int book_on_hand_status) {
        this.book_copy_id = book_copy_id;
        this.libCardId = lib_card_id;
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.refund_date = refund_date;
        this.bookOnHandStatus = book_on_hand_status;
    }

    public int getBook_copy_id() {
        return book_copy_id;
    }

    public void setBook_copy_id(int book_copy_id) {
        this.book_copy_id = book_copy_id;
    }

    public int getLibCardId() {
        return libCardId;
    }

    public void setLibCardId(int lib_card_id) {
        this.libCardId = lib_card_id;
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

    public int getBookOnHandStatus() {
        return bookOnHandStatus;
    }

    public void setBookOnHandStatus(int book_on_hand_status) {
        this.bookOnHandStatus = book_on_hand_status;
    }
}
