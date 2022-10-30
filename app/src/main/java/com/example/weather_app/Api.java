package com.example.weather_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("weather")
    Call<WeatherMain> getweather(@Query("q") String cityname,
                                 @Query("appid") String apikey);
}
