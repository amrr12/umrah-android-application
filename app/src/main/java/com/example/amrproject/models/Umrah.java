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


    private List<Mootamar> listMootamar;

    private List<Ghorfa> listGhoraf;

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

    public List<Mootamar> getListMootamar() {
        return listMootamar;
    }

    public List<Ghorfa> getListGhoraf() {
        return listGhoraf;
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

    public void setListMootamar(List<Mootamar> listMootamar) {
        this.listMootamar = listMootamar;
    }

    public void setListGhoraf(List<Ghorfa> listGhoraf) {
        this.listGhoraf = listGhoraf;
    }
}
