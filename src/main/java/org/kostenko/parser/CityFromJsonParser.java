package org.kostenko.parser;

import com.google.gson.reflect.TypeToken;
import org.kostenko.model.City;

import java.util.List;

public class CityFromJsonParser extends JsonParser<City> {
    public CityFromJsonParser() {
        super(new TypeToken<List<City>>(){});
    }
}
