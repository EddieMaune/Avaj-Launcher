package za.co.wethinkcode.avaj.simulator.vehicle;

import za.co.wethinkcode.avaj.simulator.Logger;
import za.co.wethinkcode.avaj.simulator.WeatherTower;

import java.io.IOException;

public class Baloon extends Aircraft implements  Flyable{
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.name = name;
        this.coordinates = coordinates;
    }

    public void updateConditions() throws IOException {
        String weather = this.weatherTower.getWeather(this.coordinates);
        String output;

        switch (weather) {
            case "Sun":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 2,
                        this.coordinates.getHeight() + 4 > 100 ? 100 : this.coordinates.getHeight() + 4);
                output = String.format("%s(%d): nice and warm.\n",this.name, this.id);
                Logger.getLogger().log(output);
                break;

            case "Rain":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude()
                        , this.coordinates.getLatitude()
                        , this.coordinates.getHeight() - 5 < 0 ? 0 : this.coordinates.getHeight() - 5);
                output = String.format("%s(%d): i hate the rain!\n",this.name, this.id);
                Logger.getLogger().log(output);
                if (this.coordinates.getHeight() == 0) {
                    output = String.format("%s(%d) landing. Coordinates: LONG: %d LAT: %d  HEIGHT: %d\n",
                        this.name, this.id,
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight());
                    Logger.getLogger().log(output);
                    output = String.format("Tower  says: %s(%d) unregistered from weather tower.\n", this.name, this.id);
                    Logger.getLogger().log(output);
                    this.weatherTower.unregister(this);
                }
                break;

            case "Fog":
                this.coordinates = new Coordinates(this.coordinates.getLongitude()
                        , this.coordinates.getLatitude()
                        , this.coordinates.getHeight() - 3 < 0 ? 0 : this.coordinates.getHeight() - 3);
                output = String.format("%s(%d): Can't see a damn thing!\n",this.name, this.id);
                Logger.getLogger().log(output);
                if (this.coordinates.getHeight() == 0) {
                    output = String.format("%s(%d) landing. Coordinates: LONG: %d LAT: %d HEIGHT: %d\n",
                            this.name, this.id,
                            this.coordinates.getLongitude(),
                            this.coordinates.getLatitude(),
                            this.coordinates.getHeight());
                    Logger.getLogger().log(output);
                    output = String.format("Tower  says: %s(%d)  unregistered from weather tower.\n", this.name, this.id);
                    Logger.getLogger().log(output);
                    this.weatherTower.unregister(this);
                }
                break;

            case "Snow":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude()
                        , this.coordinates.getLatitude()
                        ,  this.coordinates.getHeight() - 15 < 0 ? 0 : this.coordinates.getHeight() - 15);
                output = String.format("%s(%d): Brrrrrrr its cold.\n",this.name, this.id);
                Logger.getLogger().log(output);
                if (this.coordinates.getHeight() == 0) {
                    output = String.format("%s(%d) landing. Coordinates:  LONG: %d LAT: %d HEIGHT: %d\n",
                            this.name, this.id,
                            this.coordinates.getLongitude(),
                            this.coordinates.getLatitude(),
                            this.coordinates.getHeight());
                    Logger.getLogger().log(output);
                    output = String.format("Tower  says: %s(%d) unregistered from weather tower.\n", this.name, this.id);
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
