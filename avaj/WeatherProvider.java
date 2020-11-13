package avaj;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider provider = new WeatherProvider();
    private static String[] weather = {"RAIN", "SNOW", "FOG", "SUN"};
    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return provider;
    }
    public String getCurrentWeather(Coordinates coordinates) {
        Random rand = new Random();

        if (coordinates.getHeight() > rand.nextInt(200)){  //above clouds = sun
            return weather[3]; }
        else {
            return weather[(coordinates.getLatitude() + coordinates.getLongitude()) % 3];
        }
    }
}
