package com.example.amrproject.Dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.amrproject.models.Mootamar;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MootamarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable create_mootamar(Mootamar mootamar);

    @Query("select * from mootamar_table where umrahid = :umrahid")
    Observable<List<Mootamar>> get_mootamar(int umrahid);

    @Query("select * from mootamar_table where id = :id")
    Observable<Mootamar> get_mootamarr(int id);

    @Query("update mootamar_table set fullName = :newName , price = :newPrice , phoneNumber = :newPhone")
    Completable update_mootamar(String newName,int newPrice, int newPhone);

    @Delete
    Completable delete_mootamar(Mootamar mootamar);
}
