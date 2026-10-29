//------------------------------------------------------
// Lab 9: finding and fixing SOLID violations
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
// Written by: James Harron (23807128)
//------------------------------------------------------
/**
 * <b>BONUS - your refactored code goes here.</b>
 *
 * <p>The violation: Forecast.predict does HTTP, JSON parsing and business logic at once.</p>
 *
 * <p>put your refactored classes in this package. Leave the
 * {@code violation} package untouched - the contrast is the point. At the top
 * of your main solution class write three or four sentences saying what the
 * violation was, how you know, and what your refactor changed. Name the
 * specific thing that could not change before and can change now.</p>
 */
package edu.arizona.ece373.lab09.challenge.example1.solution;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * SRP & DIP change: Separated the responsibility of converting a city name into geographic coordinates (latitude and
 * longitude) away from the forecast logic. Defined an interface (GeoCoder) and a concrete implementation
 * (PositionStackGeoCoder).
 */
interface GeoCoder {
    Coordinates getCoordinates(String city) throws IOException;

    record Coordinates(float latitude, float longitude) {}
}

class PositionStackGeoCoder implements GeoCoder {
    @Override
    public Coordinates getCoordinates(String city) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl("https://positionstack.com/geo_api.php?query=" + city));
        String rawResponse = request.execute().parseAsString();
        JSONObject jsonObject = new JSONObject(rawResponse);

        float latitude = jsonObject.getJSONArray("data").getJSONObject(0).getFloat("latitude");
        float longitude = jsonObject.getJSONArray("data").getJSONObject(0).getFloat("longitude");

        return new Coordinates(latitude, longitude);
    }
}

/**
 * SRP & ISP change: Extracted the Open-Meteo HTTP request and raw JSON parsing into its own dedicated weather fetcher
 * service. This isolates third-party API communication from prediction business logic.
 */
interface WeatherFetcher {
    DailyForecast fetchForecast(float latitude, float longitude) throws IOException;

    record DailyForecast(JSONArray times, JSONArray weatherCodes, JSONArray windSpeeds) {}
}

class OpenMeteoWeatherFetcher implements WeatherFetcher {
    @Override
    public DailyForecast fetchForecast(float latitude, float longitude) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl("https://api.open-meteo.com/v1/forecast?latitude=" + latitude
                        + "&longitude=" + longitude
                        + "&daily=weathercode,windspeed_10m_max&current_weather=true&timezone=America%2FPhoenix"));

        String rawResponse = request.execute().parseAsString();
        JSONObject results = new JSONObject(rawResponse).getJSONObject("daily");

        return new DailyForecast(
                results.getJSONArray("time"),
                results.getJSONArray("weathercode"),
                results.getJSONArray("windspeed_10m_max")
        );
    }
}