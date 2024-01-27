package org.kostenko.model;

import com.google.gson.annotations.Expose;

public class WeatherForecast {
    private Integer time;
    private Integer wind;
    private Integer visibility;

    public WeatherForecast(Integer time, Integer wind, Integer visibility) {
        this.time = time;
        this.wind = wind;
        this.visibility = visibility;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getWind() {
        return wind;
    }

    public void setWind(Integer wind) {
        this.wind = wind;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }
}
