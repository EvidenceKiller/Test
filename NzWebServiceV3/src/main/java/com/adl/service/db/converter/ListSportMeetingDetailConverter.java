package com.adl.service.db.converter;

import com.adl.service.entity.MeetGroupDetailsEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportMeetingDetailConverter {

    @TypeConverter
    public static List<MeetGroupDetailsEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<MeetGroupDetailsEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<MeetGroupDetailsEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
