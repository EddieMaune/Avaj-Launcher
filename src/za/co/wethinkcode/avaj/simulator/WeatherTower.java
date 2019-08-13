package za.co.wethinkcode.avaj.simulator;

import za.co.wethinkcode.avaj.simulator.vehicle.Coordinates;
import za.co.wethinkcode.avaj.weather.WeatherProvider;

import java.io.IOException;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

     void changeWeather() throws IOException {
        conditionsChanged();
     }
}
