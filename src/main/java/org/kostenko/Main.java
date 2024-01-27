package org.kostenko;

import org.kostenko.model.City;
import org.kostenko.model.Flight;
import org.kostenko.model.WeatherForecast;
import org.kostenko.parser.CityFromJsonParser;
import org.kostenko.parser.FlightFromJsonParser;
import org.kostenko.parser.JsonParser;
import org.kostenko.util.PropertiesUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static final String userDir = System.getProperty("user.dir") + File.separator;
    static final Map<String, Integer> diff = Stream.of(new Object[][]{
            {"moscow", 0},
            {"novosibirsk", 4},
            {"omsk", 3},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
    public static final String format = "%-5s | %-20s -> %-20s | %s\n";
    static Map<String, List<WeatherForecast>> forecastMap;
    static List<Flight> flights;

    public static void main(String[] args) throws FileNotFoundException {
        JsonParser<Flight> flightParser = new FlightFromJsonParser();
        JsonParser<City> cityParser = new CityFromJsonParser();
        assignFlights(flightParser);
        assignWeather(cityParser);
        for (Flight flight : flights) {
            System.out.printf(format,
                    flight.getNumberOfDeparture(),
                    flight.getFrom(), flight.getTo(),
                    isFlightInTime(flight) ? "по расписанию" : "отменен");
        }
    }

    private static void assignWeather(JsonParser<City> cityParser) throws FileNotFoundException {
        forecastMap = new HashMap<>();
        List<City> cities = cityParser.getData(userDir + PropertiesUtil.getProperty("weather.source"),
                PropertiesUtil.getProperty("weather.key"));
        for (City city : cities) {
            forecastMap.put(city.getNameOfCity(), city.getWeatherForecast());
        }
    }

    private static void assignFlights(JsonParser<Flight> flightParser) throws FileNotFoundException {
        flights = flightParser.getData(userDir + PropertiesUtil.getProperty("flight.source"),
                PropertiesUtil.getProperty("flight.key"));
    }

    static boolean isFlightInTime(Flight flight) {
        int timeOfLanding = (flight.getDeparture() + flight.getDuration() + (diff.get(flight.getTo()) - diff.get(flight.getFrom()))) % 25;
        if (!isWeatherOk(getWeatherInCity(flight.getFrom(), flight.getDeparture()))
                || !isWeatherOk(getWeatherInCity(flight.getTo(), timeOfLanding))) return false;
        return true;
    }

    static WeatherForecast getWeatherInCity(String city, int time) {
        for (WeatherForecast weather : forecastMap.get(city)) {
            if (weather.getTime().equals(time)) return weather;
        }
        throw new NoSuchElementException();
    }

    static boolean isWeatherOk(WeatherForecast weather) {
        return weather.getWind() <= Integer.parseInt(PropertiesUtil.getProperty("weather.wind.indicator"))
                && weather.getVisibility() >= Integer.parseInt(PropertiesUtil.getProperty("weather.visibility.indicator"));
    }

}