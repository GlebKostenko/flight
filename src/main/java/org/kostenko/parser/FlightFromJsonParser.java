package org.kostenko.parser;

import com.google.gson.reflect.TypeToken;
import org.kostenko.model.City;
import org.kostenko.model.Flight;

import java.util.List;

public class FlightFromJsonParser extends JsonParser<Flight> {
    public FlightFromJsonParser() {
        super(new TypeToken<List<Flight>>(){});
    }
}
