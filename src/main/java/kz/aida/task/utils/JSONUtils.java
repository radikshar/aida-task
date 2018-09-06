package kz.aida.task.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import kz.aida.task.database.BusJourney;

public class JSONUtils {

    public static String busJourneyToJson(BusJourney busJourney) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(busJourney);
    }

    public static BusJourney toBusJourney(String json) {
        Gson gson = new GsonBuilder().create();

        try {
            return gson.fromJson(json, BusJourney.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}
