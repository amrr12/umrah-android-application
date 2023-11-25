package com.example.amrproject.Converters;
import androidx.room.TypeConverter;

import com.example.amrproject.models.Ghorfa;
import com.example.amrproject.models.Mootamar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
public class GhorfaListConverter {

    @TypeConverter
    public static List<Ghorfa> fromString(String value) {
        Type listType = new TypeToken<List<Ghorfa>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Ghorfa> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
