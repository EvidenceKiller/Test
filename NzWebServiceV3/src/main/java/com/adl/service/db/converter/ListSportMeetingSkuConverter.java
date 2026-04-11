package com.adl.service.db.converter;

import com.adl.service.entity.MeetSkuEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportMeetingSkuConverter {

    @TypeConverter
    public static List<MeetSkuEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<MeetSkuEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<MeetSkuEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
