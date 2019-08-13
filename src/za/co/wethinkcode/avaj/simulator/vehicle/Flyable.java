package za.co.wethinkcode.avaj.simulator.vehicle;

import za.co.wethinkcode.avaj.simulator.WeatherTower;

import java.io.IOException;

public interface Flyable {
    public void updateConditions() throws IOException;
    public void registerTower(WeatherTower WeatherTower) throws IOException;
}
