package com.example.weather_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weathers {
    @SerializedName("id")
    private Integer id;

    @SerializedName("main")
    private String main;

    @SerializedName("description")
    private String desc;

    @SerializedName("icon")
    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
