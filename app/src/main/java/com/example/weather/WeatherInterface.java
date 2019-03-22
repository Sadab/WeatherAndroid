package com.example.weather;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {

    @GET("data/2.5/weather")
    Call<MainModel> getMainModelData(@Query("q") String q,
                               @Query("appid") String app,
                               @Query("units") String unit);

    @GET("data/2.5/weather")
    Call<WeatherModel> getWeatherModelData(@Query("q") String q,
                                           @Query("appid") String app,
                                           @Query("units") String unit);


}
