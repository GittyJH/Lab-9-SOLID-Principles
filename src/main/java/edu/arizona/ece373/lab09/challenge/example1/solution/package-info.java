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

/**
 * SRP & OCP change: Extracted the translation map and code-to-text logic into its own class.
 */
interface WeatherTranslator {
    String translate(int weatherCode);
}

class WmoWeatherTranslator implements WeatherTranslator {
    private static final Map<Integer, String> TRANSLATION = new HashMap<>() {{
        put(0, "lear sky");
        put(1, "Mainly clear, partly cloudy, and overcast");
        put(2, "Mainly clear, partly cloudy, and overcast");
        put(3, "Mainly clear, partly cloudy, and overcast");
        put(45, "Fog and depositing rime fog");
        put(48, "Fog and depositing rime fog");
        put(51, "Drizzle: Light, moderate, and dense intensity");
        put(53, "Drizzle: Light, moderate, and dense intensity");
        put(55, "Drizzle: Light, moderate, and dense intensity");
        put(56, "Freezing Drizzle: Light and dense intensity");
        put(57, "Freezing Drizzle: Light and dense intensity");
        put(61, "Rain: Slight, moderate and heavy intensity");
        put(63, "Rain: Slight, moderate and heavy intensity");
        put(65, "Rain: Slight, moderate and heavy intensity");
        put(66, "Freezing Rain: Light and heavy intensity");
        put(67, "Freezing Rain: Light and heavy intensity");
        put(71, "Snow fall: Slight, moderate, and heavy intensity");
        put(73, "Snow fall: Slight, moderate, and heavy intensity");
        put(75, "Snow fall: Slight, moderate, and heavy intensity");
        put(77, "Snow grains");
        put(80, "Rain showers: Slight, moderate, and violent");
        put(81, "Rain showers: Slight, moderate, and violent");
        put(82, "Rain showers: Slight, moderate, and violent");
        put(85, "Snow showers slight and heavy");
        put(86, "Snow showers slight and heavy");
        put(95, "Thunderstorm: Slight or moderate");
        put(96, "Thunderstorm with slight and heavy hail");
        put(99, "Thunderstorm with slight and heavy hail");
    }};

    @Override
    public String translate(int weatherCode) {
        return TRANSLATION.get(weatherCode);
    }
}

/**
 * The original violation was a monolithic God class that coupled HTTP transport, JSON parsing,
 * coordinate lookup, weather retrieval, and date filtering all within a single predict method.
 * I know this violates the Single Responsibility Principle because changing the third-party API
 * or adding unit tests required mocking network transports and JSON parsing directly inside the prediction logic.
 * The refactor extracted these concerns into discrete, decoupled service interfaces (GeoCoder, WeatherFetcher,
 * and WeatherTranslator). Specifically, before the refactor, you could not swap out
 * the PositionStack API or Open-Meteo backend without rewriting the core prediction method; now, you can
 * substitute any implementation of GeoCoder or WeatherFetcher without changing a single line of the core
 * forecasting workflow.
 */
class Forecast {
    private final GeoCoder geoCoder;
    private final WeatherFetcher weatherFetcher;
    private final WeatherTranslator weatherTranslator;

    // Default constructor preserving the original instantiation behavior
    public Forecast() {
        this.geoCoder = new PositionStackGeoCoder();
        this.weatherFetcher = new OpenMeteoWeatherFetcher();
        this.weatherTranslator = new WmoWeatherTranslator();
    }

    // Constructor injection for high testability and loose coupling (DIP)
    public Forecast(GeoCoder geoCoder, WeatherFetcher weatherFetcher, WeatherTranslator weatherTranslator) {
        this.geoCoder = geoCoder;
        this.weatherFetcher = weatherFetcher;
        this.weatherTranslator = weatherTranslator;
    }

    public String predict(String city, LocalDate datetime, boolean wind) throws IOException {
        // When date is not provided we look for the current prediction
        if (datetime == null) {
            datetime = LocalDate.now();
        }
        String format = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // If there are predictions within the valid range
        if (datetime.isBefore(LocalDate.now().plusDays(7))) {
            GeoCoder.Coordinates coords = geoCoder.getCoordinates(city);

            WeatherFetcher.DailyForecast results = weatherFetcher.fetchForecast(coords.latitude(), coords.longitude());

            JSONArray times = results.times();
            for (int i = 0; i < times.length(); i++) {
                // When the date matches the expected date
                if (format.equals(times.get(i))) {
                    // If we have to return the wind information
                    if (wind) {
                        return String.valueOf(results.windSpeeds().getFloat(i));
                    } else {
                        int weatherCode = results.weatherCodes().getInt(i);
                        return weatherTranslator.translate(weatherCode);
                    }
                }
            }
        }
        return "";
    }
}