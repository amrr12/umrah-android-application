package com.example.amrproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mootamar_table")
public class Mootamar {
    @PrimaryKey(autoGenerate = true)
    private int id ;

    private String fullName ;

    private int phoneNumber ;

    private int price ;

    private int gendre;

    private int ghorfaid;

    private int umrahid;

    public Mootamar(String fullName, int phoneNumber, int price, int gendre, int ghorfaid, int umrahid) {

        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.gendre = gendre;
        this.ghorfaid = ghorfaid;
        this.umrahid = umrahid;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getPrice() {
        return price;
    }

    public int getGendre() {
        return gendre;
    }

    public int getGhorfaid() {
        return ghorfaid;
    }

    public int getUmrahid() {
        return umrahid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setGendre(int gendre) {
        this.gendre = gendre;
    }

    public void setGhorfaId(int ghorfaId) {
        this.ghorfaid = ghorfaId;
    }

    public void setUmrah(int umrahId) {
        this.umrahid = umrahId;
    }
}
