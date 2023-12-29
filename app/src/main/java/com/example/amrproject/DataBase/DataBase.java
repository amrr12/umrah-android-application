package com.example.amrproject.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.amrproject.Converters.GhorfaConverter;
import com.example.amrproject.Converters.MootamarListConverter;
import com.example.amrproject.Converters.GhorfaListConverter;
import com.example.amrproject.Converters.UmrahConverter;
import com.example.amrproject.Dao.GhorfaDao;
import com.example.amrproject.Dao.MootamarDao;
import com.example.amrproject.Dao.UmrahDao;
import com.example.amrproject.models.Ghorfa;
import com.example.amrproject.models.Mootamar;
import com.example.amrproject.models.Umrah;

@Database(entities =  {Umrah.class, Mootamar.class, Ghorfa.class}, version = 8)
@TypeConverters(value = {MootamarListConverter.class, GhorfaListConverter.class, GhorfaConverter.class, UmrahConverter.class})
abstract public class DataBase extends RoomDatabase {

    private static DataBase instance;

    public abstract UmrahDao umrahDao();
    public abstract MootamarDao mootamarDao();

    public abstract GhorfaDao ghorfaDao();


    public static synchronized DataBase getInstance(Context context){

         if (instance == null) {
             instance = Room.databaseBuilder(context.getApplicationContext(),DataBase.class,"umrahAppDataBase")
                     .fallbackToDestructiveMigration()
                     .build();
         }

         return instance;
    };

}
