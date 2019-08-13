package za.co.wethinkcode.avaj.weather;

import za.co.wethinkcode.avaj.simulator.vehicle.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static String weather[];


    private WeatherProvider() {
        weather = new String[4];
        weather[0] = "Sun";
        weather[1] = "Rain";
        weather[2] = "Fog";
        weather[3] = "Snow";
    }

    public static WeatherProvider getProvider() {
        return weatherProvider == null ? (weatherProvider = new WeatherProvider()) : weatherProvider ;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int random;

        random = (coordinates.getLatitude() + coordinates.getHeight() + coordinates.getLongitude()) % 4;
        return weatherProvider != null ? weather[random] : null;
    }





}
