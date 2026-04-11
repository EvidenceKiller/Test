package com.adl.service.db.converter;

import com.adl.service.entity.SportRecordEntity;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class RecordMeetingConverter {

    @TypeConverter
    public static SportRecordEntity.RecordMeeting stringToObject(String value) {
        return new Gson().fromJson(value, SportRecordEntity.RecordMeeting.class);
    }

    @TypeConverter
    public static String objectToString(SportRecordEntity.RecordMeeting json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
