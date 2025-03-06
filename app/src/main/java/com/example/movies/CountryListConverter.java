package com.example.movies;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class CountryListConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<Country> toCountryList(String value) {
        Type listType = new TypeToken<List<Country>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromCountryList(List<Country> list) {
        return gson.toJson(list);
    }
}
