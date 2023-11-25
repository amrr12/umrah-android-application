package com.example.amrproject.Converters;
import androidx.room.TypeConverter;

import com.example.amrproject.models.Ghorfa;
import com.google.gson.Gson;
public class GhorfaConverter {

    @TypeConverter
    public static Ghorfa fromString(String value) {
        return new Gson().fromJson(value, Ghorfa.class);
    }

    @TypeConverter
    public static String fromGhorfa(Ghorfa ghorfa) {
        Gson gson = new Gson();
        return gson.toJson(ghorfa);
    }
}
