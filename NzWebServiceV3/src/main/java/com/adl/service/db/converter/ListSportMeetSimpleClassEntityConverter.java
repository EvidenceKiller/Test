package com.adl.service.db.converter;

import com.adl.service.db.entity.SportMeetSimpleClassEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportMeetSimpleClassEntityConverter {

    @TypeConverter
    public static List<SportMeetSimpleClassEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<SportMeetSimpleClassEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportMeetSimpleClassEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
