package com.example.auto_information_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "placed_book_copies")
public class PlacedBookCopies {
    @Id
    private int book_copy_id;
    @Column(nullable = false)
    private int cell_id;

    public PlacedBookCopies() {
    }

    public PlacedBookCopies(int book_copy_id, int cell_id) {
        this.book_copy_id = book_copy_id;
        this.cell_id = cell_id;
    }

    public int getBook_copy_id() {
        return book_copy_id;
    }

    public void setBook_copy_id(int book_copy_id) {
        this.book_copy_id = book_copy_id;
    }

    public int getCell_id() {
        return cell_id;
    }

    public void setCell_id(int cell_id) {
        this.cell_id = cell_id;
    }
}
