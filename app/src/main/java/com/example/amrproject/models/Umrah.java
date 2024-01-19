package com.example.amrproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.Date;
import java.util.List;

@Entity(tableName = "umrah_table")
public class Umrah {

    @PrimaryKey(autoGenerate = true)
    private int  id ;
    private String date ;

    private String hotel;

    private int takalif ;



    public Umrah(String date, String hotel, int takalif) {
        this.date = date;
        this.hotel = hotel;
        this.takalif = takalif;
    }


    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHotel() {
        return hotel;
    }

    public int getTakalif() {
        return takalif;
    }




    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public void setTakalif(int takalif) {
        this.takalif = takalif;
    }

}
