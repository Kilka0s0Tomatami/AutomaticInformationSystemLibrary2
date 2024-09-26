package com.example.auto_information_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lib_cards")
public class LibCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lib_card_id;
    @Column(nullable = false)
    private int user_id;
    @Column(nullable = false)
    private String lib_card_first_name;
    @Column(nullable = false)
    private String lib_card_second_name;
    private String lib_card_father_name;
    private int lib_card_mobilephone_numner;
    private int lib_card_homephone_number;
}

