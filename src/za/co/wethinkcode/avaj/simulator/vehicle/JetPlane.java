package za.co.wethinkcode.avaj.simulator.vehicle;

import za.co.wethinkcode.avaj.simulator.Logger;
import za.co.wethinkcode.avaj.simulator.WeatherTower;

import java.io.IOException;

public class JetPlane extends Aircraft implements  Flyable {
   private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.name = name;
        this.coordinates = coordinates;
    }

    public void updateConditions() throws IOException {
        String output;
        String weather = this.weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "Sun":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 10,
                        this.coordinates.getHeight() + 2 > 100 ? 100 : this.coordinates.getHeight() + 2);
               output = String.format("%s(%d): High and dry sun is out, awesome!\n",this.name, this.id);
                Logger.getLogger().log(output);
                break;
            case "Rain":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude()
                        , this.coordinates.getLatitude() + 5
                        , this.coordinates.getHeight());
                output = String.format("%s(%d): Its raining its pouring the old man is snoring!\n",this.name, this.id);
                Logger.getLogger().log(output);
                break;
            case "Fog":
                this.coordinates = new Coordinates(this.coordinates.getLongitude()
                        , this.coordinates.getLatitude() + 1
                        , this.coordinates.getHeight());
                output = String.format("%s(%d): I can't see a thing!\n",this.name, this.id);
                Logger.getLogger().log(output);
                break;
            case "Snow":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude()
                        , this.coordinates.getLatitude()
                        ,  this.coordinates.getHeight() - 7 < 0 ? 0 : this.coordinates.getHeight() - 7);
                output = String.format("%s(%d): Its so cold.\n",this.name, this.id);
                Logger.getLogger().log(output);
                if (this.coordinates.getHeight() == 0) {
                    output = String.format("%s(%d) landing. Coordinates: LONG: %d LAT: %d HEIGHT: %d\n",
                            this.name, this.id,
                            this.coordinates.getLongitude(),
                            this.coordinates.getLatitude(),
                            this.coordinates.getHeight());
                    Logger.getLogger().log(output);
                    output = String.format("Tower says: %s(%d) unregistered from weather tower.\n", this.name, this.id);
                    Logger.getLogger().log(output);
                    this.weatherTower.unregister(this);
                }
                break;

        }
    }

    public void registerTower(WeatherTower WeatherTower) throws IOException {
        this.weatherTower = WeatherTower;
        String output;

        output = String.format("Tower says: %s (%d) registered to weather tower\n",this.name , this.id );
        Logger.getLogger().log(output);
        this.weatherTower.register(this);
    }
}
