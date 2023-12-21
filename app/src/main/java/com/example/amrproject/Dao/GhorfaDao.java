package com.example.amrproject.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.amrproject.models.Ghorfa;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface GhorfaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable create_ghorfa(Ghorfa ghorfa);

    @Query("select * from ghorfa_table")
    Observable<List<Ghorfa>> get_ghoraf();

    @Query("SELECT * FROM ghorfa_table WHERE id = :id")
    Observable<Ghorfa> find_ghorfa_by_id(int id);

    @Delete
    Completable delete_Ghorfa(Ghorfa ghorfa);

}
