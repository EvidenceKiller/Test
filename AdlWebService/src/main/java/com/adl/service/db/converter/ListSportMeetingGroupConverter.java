package com.adl.service.db.converter;

import androidx.room.TypeConverter;

import com.adl.service.entity.MeetGroupTeamEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListSportMeetingGroupConverter {

    @TypeConverter
    public static List<MeetGroupTeamEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<MeetGroupTeamEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<MeetGroupTeamEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
