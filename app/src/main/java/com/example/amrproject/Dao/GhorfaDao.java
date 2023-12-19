package com.example.amrproject.Dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.amrproject.models.Ghorfa;
import com.example.amrproject.models.Umrah;

import io.reactivex.rxjava3.core.Completable;

public interface GhorfaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable create_ghorfa(Ghorfa ghorfa);


}
