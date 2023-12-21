package com.example.amrproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;
@Entity(tableName = "ghorfa_table")
public class Ghorfa {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String type;
    private int umrahid;

    @Relation(parentColumn = "id", entityColumn = "ghorfaid")
    private List<Mootamar> mootamars;

    public Ghorfa(String type, int umrahid) {
        this.type = type;
        this.umrahid = umrahid;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getUmrahid() {
        return umrahid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setUmrahid(int umrahid) {
        this.umrahid = umrahid;
    }
}
