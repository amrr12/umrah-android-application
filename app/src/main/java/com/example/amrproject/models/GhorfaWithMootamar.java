package com.example.amrproject.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class GhorfaWithMootamar {
    @Embedded public Ghorfa ghorfa;
    @Relation(
            parentColumn = "id",
            entityColumn = "ghorfaid"
    )
    public List<Mootamar> mootamarList;

}
