package org.kostenko.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import org.kostenko.model.City;
import org.kostenko.model.WeatherForecast;
import org.kostenko.parser.JsonParser;
import org.kostenko.parser.WeatherForecastFromJsonParser;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CityAdapter implements JsonDeserializer<City> {
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) {
        String cityName = json.getAsJsonObject().entrySet().stream().findFirst().get().getKey();
        JsonParser<WeatherForecast> weatherParser = new WeatherForecastFromJsonParser();
        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        try {
            weatherForecasts = weatherParser.getData(PropertiesUtil.getProperty("weather.source"),
                    PropertiesUtil.getProperty("weather.key"), cityName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new City(cityName, weatherForecasts);
    }
}
