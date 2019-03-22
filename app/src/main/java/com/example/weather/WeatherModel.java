package com.example.weather;

import com.google.gson.JsonObject;

public class WeatherModel {
    private int temp;
    private JsonObject main;
    public MainModel mainModel;

    public int getTemp() {
        return temp;
    }

    public JsonObject getMain() {
        return main;
    }

    public MainModel getMainModel() {
        return mainModel;
    }
}
