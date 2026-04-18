package com.adl.service.db.converter;

import com.adl.service.db.entity.SportMeetGroupDetailEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportMeetGroupDetailEntityConverter {

    @TypeConverter
    public static List<SportMeetGroupDetailEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<SportMeetGroupDetailEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportMeetGroupDetailEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
