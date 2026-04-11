package com.adl.service.db.converter;

import androidx.room.TypeConverter;

import com.adl.service.entity.SportRecordEntity;
import com.google.gson.Gson;

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
