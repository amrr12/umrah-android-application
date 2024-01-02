package com.example.amrproject.models;

import java.util.Objects;

public class MootamarAR {

    private String fullname;
    private int phoneNumber;
    private int gender;



    public MootamarAR() {

    }
    public MootamarAR(String fullname, int gender, int phonenumber) {
        this.fullname = fullname;
        this.gender = gender;
        this.phoneNumber = phonenumber;
    }

    public String getFullname() {
        return fullname;
    }

    public int getGender() {
        return gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(int phonenumber) {
        this.phoneNumber = phonenumber;
    }
}
