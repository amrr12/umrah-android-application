package com.example.amrproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.util.FtsTableInfo;

import com.example.amrproject.models.Umrah;
import com.example.amrproject.models.UmrahWithMootamar;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UmrahDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   Completable create_umrah(Umrah umrah);

   @Query("select * from umrah_table")
   Observable<List<Umrah>> get_umrah();
   @Query("SELECT * FROM umrah_table WHERE id = :id")
   Observable<UmrahWithMootamar> find_umrah_by_id(int id);

   @Query("UPDATE umrah_table SET  takalif = :price, hotel = :hotel WHERE id = :id")
   Completable update_umrah(int id,String hotel,int price);

   @Delete
   Completable delete_umrah(Umrah umrah);

}
