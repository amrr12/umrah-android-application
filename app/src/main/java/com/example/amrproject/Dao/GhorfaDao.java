package com.example.amrproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.amrproject.models.Ghorfa;
import com.example.amrproject.models.GhorfaWithMootamar;
import com.example.amrproject.models.Mootamar;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface GhorfaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable create_ghorfa(Ghorfa ghorfa);

    @Transaction
    @Query("select * from ghorfa_table")
    Observable<List<GhorfaWithMootamar>> get_ghoraf();

    @Query("select id from GHORFA_TABLE ORDER BY id DESC\n" +
            "LIMIT 1;")
    Observable<List<Integer>> getLastGhorfa();

    @Transaction
    @Query("SELECT * FROM ghorfa_table WHERE id = :id")
    Observable<List<GhorfaWithMootamar>> find_ghorfa_by_id(int id);

    @Transaction
    @Query("Select * from ghorfa_table where type = :type")
    Observable<List<GhorfaWithMootamar>> find_ghorfa_by_type(String type);



    @Delete
    Completable delete_Ghorfa(Ghorfa ghorfa);

}
