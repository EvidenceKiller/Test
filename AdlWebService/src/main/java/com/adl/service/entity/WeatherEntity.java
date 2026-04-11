package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

public class WeatherEntity implements Serializable {
    /**
     * "locationId": "101270101",
     *         "locationName": "成都",
     *         "date": 1753891200000,
     *         "weatherNow": {
     *             "obsTime": "2025-07-31T11:13+08:00",
     *             "temp": "34",
     *             "feelsLike": "38",
     *             "icon": "100",
     *             "text": "晴",
     *             "wind360": "225",
     *             "windDir": "西南风",
     *             "windScale": "2",
     *             "windSpeed": "6",
     *             "humidity": "56",
     *             "precip": "0.0",
     *             "pressure": "943",
     *             "vis": "10",
     *             "cloud": "6",
     *             "dew": "24"
     *         },
     *         "airquality": [
     *             {
     *                 "code": "cn-mee",
     *                 "name": "AQI (CN)",
     *                 "aqi": "38",
     *                 "aqiDisplay": "38",
     *                 "level": "1",
     *                 "category": "优"
     *             }
     *         ]
     */
    public String locationId;
    public String locationName;
    public long date;
    public WeatherNowEntity weatherNow;
    public List<AirqualityEntity> airquality;

    public static class WeatherNowEntity {
        public String obsTime;
        public String temp;
        public String feelsLike;
        public String icon;
        public String text;
        public String wind360;
        public String windDir;
        public String windScale;
        public String windSpeed;
        public String humidity;
        public String precip;
        public String pressure;
        public String vis;
        public String cloud;
        public String dew;
    }
    public static class AirqualityEntity {
        public String code;
        public String name;
        public String aqi;
        public String aqiDisplay;
        public String level;
        public String category;
    }
}
