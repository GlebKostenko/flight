package org.kostenko.parser;

import com.google.gson.reflect.TypeToken;
import org.kostenko.model.WeatherForecast;

import java.util.List;

public class WeatherForecastFromJsonParser extends JsonParser<WeatherForecast> {
    public WeatherForecastFromJsonParser() {
        super(new TypeToken<List<WeatherForecast>>() {});
    }
}
