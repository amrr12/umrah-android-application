package com.example.amrproject.Converters;
import androidx.room.TypeConverter;

import com.example.amrproject.models.Umrah;
import com.google.gson.Gson;
public class UmrahConverter {

    @TypeConverter
    public static Umrah fromString(String value) {
        return new Gson().fromJson(value, Umrah.class);
    }

    @TypeConverter
    public static String fromGhorfa(Umrah umrah) {
        Gson gson = new Gson();
        return gson.toJson(umrah);
    }
}

