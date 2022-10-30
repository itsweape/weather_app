package com.example.weather_app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherMain {
    @SerializedName("main")
    WeatherData main;

    @SerializedName("weather")
    List<Weathers> weathers;

    public List<Weathers> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weathers> weathers) {

        this.weathers = weathers;
    }

    public WeatherData getMain() {

        return main;
    }

    public void setMain(WeatherData main) {

        this.main = main;
    }

}
