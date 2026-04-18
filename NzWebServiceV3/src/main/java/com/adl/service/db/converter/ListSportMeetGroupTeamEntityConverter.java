package com.adl.service.db.converter;

import com.adl.service.db.entity.SportMeetGroupTeamEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListSportMeetGroupTeamEntityConverter {

    @TypeConverter
    public static List<SportMeetGroupTeamEntity> stringToObject(String value) {
        Type listType = new TypeToken<List<SportMeetGroupTeamEntity>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String objectToString(List<SportMeetGroupTeamEntity> json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
