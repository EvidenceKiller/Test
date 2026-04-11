package com.adl.service.db.converter;

import com.adl.service.entity.SportRecordEntity;
import com.google.gson.Gson;

import androidx.room.TypeConverter;

public class RecordCompetitionConverter {

    @TypeConverter
    public static SportRecordEntity.RecordCompetition stringToObject(String value) {
        return new Gson().fromJson(value, SportRecordEntity.RecordCompetition.class);
    }

    @TypeConverter
    public static String objectToString(SportRecordEntity.RecordCompetition json) {
        Gson gson = new Gson();
        return gson.toJson(json);
    }
}
