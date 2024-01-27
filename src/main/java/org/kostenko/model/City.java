package org.kostenko.model;

import java.util.List;

public class City {
    String nameOfCity;
    List<WeatherForecast> weatherForecast;

    public City(String nameOfCity, List<WeatherForecast> weatherForecast) {
        this.nameOfCity = nameOfCity;
        this.weatherForecast = weatherForecast;
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    public List<WeatherForecast> getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(List<WeatherForecast> weatherForecast) {
        this.weatherForecast = weatherForecast;
    }
}
