package com.example.amrproject.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UmrahWithGhoraf {
    @Embedded
    public Umrah umrah;
    @Relation(
            parentColumn = "id",
            entityColumn = "umrahid"
    )
    public List<GhorfaWithMootamar> ghoraf;
}
