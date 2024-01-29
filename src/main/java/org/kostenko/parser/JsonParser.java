package org.kostenko.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.internal.NotNull;
import org.kostenko.model.City;
import org.kostenko.util.CityAdapter;

public abstract class JsonParser<T> {
    static Gson parseTool = new GsonBuilder()
            .registerTypeAdapter(City.class, new CityAdapter())
            .create();
    TypeToken<List<T>> token;

    JsonParser(TypeToken<List<T>> token) {
        this.token = token;
    }

    private JsonElement parse(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return parseTool.fromJson(reader, JsonObject.class);
    }

    private JsonElement parse(String file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return parseTool.fromJson(reader, JsonObject.class);
    }

    private List<T> getData(JsonElement source, String... keys) {
        for (String key : keys) {
            if (source instanceof JsonObject) {
                source = getElementByKey((JsonObject) source, key);
            } else {
                for (int i = 0; i < ((JsonArray) source).size(); i++) {
                    JsonObject json = ((JsonArray) source).get(i).getAsJsonObject();
                    if (json.get(key) != null){
                        source = getElementByKey(json, key);
                        break;
                    }
                }
            }
        }
        return parseTool.fromJson(source, token);
    }

    private JsonElement getElementByKey(JsonObject source, String key) {
        try {
            return source.getAsJsonArray(key);
        } catch (ClassCastException e){
            return source.getAsJsonObject(key);
        }
    }

    public List<T> getData(String source, @NotNull String... keys) throws FileNotFoundException {
        return getData(parse(source), keys);
    }
}
