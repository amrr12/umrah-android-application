package com.example.amrproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "ghorfa_table")
public class Ghorfa {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String type;
    private List<Mootamar> roommates;
    private Umrah umrah;

    public Ghorfa(String type, List<Mootamar> roommates, Umrah umrah) {
        this.type = type;
        this.roommates = roommates;
        this.umrah = umrah;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<Mootamar> getRoommates() {
        return roommates;
    }

    public Umrah getUmrah() {
        return umrah;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoommates(List<Mootamar> roommates) {
        this.roommates = roommates;
    }

    public void setUmrah(Umrah umrah) {
        this.umrah = umrah;
    }
}
