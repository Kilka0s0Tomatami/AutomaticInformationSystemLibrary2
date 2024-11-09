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
    @Column(nullable = false, name = "user_id")
    private int userId;
    @Column(nullable = false)
    private String lib_card_first_name;
    @Column(nullable = false)
    private String lib_card_second_name;
    private String lib_card_father_name;
    private int lib_card_mobilephone_number;
    private int lib_card_homephone_number;

    //конструкторы
    public LibCards(){};
    public LibCards(int user_id, String lib_card_first_name,String lib_card_second_name, String lib_card_father_name,
                    int lib_card_mobilephone_number, int lib_card_homephone_number){
    this.userId=user_id;
    this.lib_card_first_name = lib_card_first_name;
    this.lib_card_second_name = lib_card_second_name;
    this.lib_card_father_name = lib_card_father_name;
    this.lib_card_mobilephone_number = lib_card_mobilephone_number;
    this.lib_card_homephone_number =lib_card_homephone_number;                   
    }

    //гетеры и сеттеры
    public int getLib_card_id(){
        return this.lib_card_id;
    }
    public int getUserId(){
        return this.userId;
    }
    public void setUserId(int user_id){
        this.userId=user_id;
    }
    public String getLib_card_first_name(){
        return this.lib_card_first_name;
    }
    public void setLib_card_first_name(String LibCardFirstName){
        this.lib_card_first_name = LibCardFirstName;
    }
    public String getLib_card_second_name(){
        return this.lib_card_second_name;
    }
    public void setLib_card_second_name(String LibCardSecondName){
        this.lib_card_second_name = LibCardSecondName;
    }
    public String getLib_card_father_name(){
        return this.lib_card_father_name;
    }
    public void setLib_card_father_name(String LibCardFatherName){
        this.lib_card_father_name = LibCardFatherName;
    }
    public int getLib_card_mobilephone_number(){
        return this.lib_card_mobilephone_number;
    }
    public void setLib_card_mobilephone_number(int lib_card_mobilephone_number ){
        this.lib_card_mobilephone_number = lib_card_mobilephone_number;
    }
    public int getLib_card_homephone_number(){
        return this.lib_card_homephone_number;
    }
    public void setLib_card_homephone_number(int lib_card_homephone_number ){
        this.lib_card_homephone_number = lib_card_homephone_number;
    }
}

