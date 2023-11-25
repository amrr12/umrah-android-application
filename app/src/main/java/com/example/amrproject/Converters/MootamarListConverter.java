package com.example.amrproject.Converters;
import androidx.room.TypeConverter;

import com.example.amrproject.models.Mootamar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
public class MootamarListConverter {
    @TypeConverter
    public static List<Mootamar> fromString(String value) {
        Type listType = new TypeToken<List<Mootamar>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Mootamar> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
