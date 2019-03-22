package com.example.weather;

import com.google.gson.JsonArray;

public class WeatherModel {
    private JsonArray weather;

    public JsonArray getWeather() {
        //return  weather.get(0).getAsJsonObject().get("icon").toString();
        return weather;
    }

}
