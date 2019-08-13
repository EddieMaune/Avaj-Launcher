package za.co.wethinkcode.avaj.simulator.vehicle;

import za.co.wethinkcode.avaj.simulator.Exceptions.HeightOutOfRangeException;
import za.co.wethinkcode.avaj.simulator.Exceptions.NegativeCoordinateException;
import za.co.wethinkcode.avaj.simulator.Exceptions.UFOException;

public class AircraftFactory {
    public static Flyable newAircraft(String name, int longitude, int latitude, int height) throws NegativeCoordinateException, HeightOutOfRangeException, UFOException {
        Flyable aircraft = null;

        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new NegativeCoordinateException(String.format("Error: Coordinate must be greater than 0. Your coordinates for %s are (%d, %d, %d).", name, latitude, longitude, height));
        }
        if (height > 100) {
            throw new HeightOutOfRangeException(String.format("Error: height is out of range. Aircraft %s's  height is (%d).",name, height));
        }
        switch (name.split("#")[0]) {
            case "Helicopter":
                aircraft = new Helicopter(name, new Coordinates(longitude, latitude, height));
                break;
            case "JetPlane":
                aircraft = new JetPlane(name, new Coordinates(longitude, latitude, height));
                break;
            case "Baloon":
                aircraft = new Baloon(name, new Coordinates(longitude, latitude, height));
                break;
            default:
                throw new UFOException(String.format("Error: %s is an unknown aircraft.", name.split("#")[0]));
        }
        return aircraft;
    }
}
