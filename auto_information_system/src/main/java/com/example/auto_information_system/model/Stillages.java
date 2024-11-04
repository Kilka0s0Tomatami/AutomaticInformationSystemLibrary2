package com.example.auto_information_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stillages")
public class Stillages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stillage_id;
    @Column(nullable = false)
    private int stillage_number;
    @Column(nullable = false)
    private int stillage_shelfs_count;
    @Column(nullable = false)
    private int stillage_shelf_cells_count;

    public Stillages() {
    }

    public Stillages(int stillage_number, int stillage_shelfs_count, int stillage_shelf_cells_count) {
        this.stillage_number = stillage_number;
        this.stillage_shelfs_count = stillage_shelfs_count;
        this.stillage_shelf_cells_count = stillage_shelf_cells_count;
    }

    public int getStillage_id() {
        return stillage_id;
    }

    public void setStillage_id(int stillage_id) {
        this.stillage_id = stillage_id;
    }

    public int getStillage_number() {
        return stillage_number;
    }

    public void setStillage_number(int stillage_number) {
        this.stillage_number = stillage_number;
    }

    public int getStillage_shelfs_count() {
        return stillage_shelfs_count;
    }

    public void setStillage_shelfs_count(int stillage_shelfs_count) {
        this.stillage_shelfs_count = stillage_shelfs_count;
    }

    public int getStillage_shelf_cells_count() {
        return stillage_shelf_cells_count;
    }

    public void setStillage_shelf_cells_count(int stillage_shelf_cells_count) {
         this.stillage_shelf_cells_count = stillage_shelf_cells_count; 
    }
    
}
