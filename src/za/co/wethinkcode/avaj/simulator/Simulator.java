package za.co.wethinkcode.avaj.simulator;

import za.co.wethinkcode.avaj.simulator.Exceptions.HeightOutOfRangeException;
import za.co.wethinkcode.avaj.simulator.Exceptions.NegativeCoordinateException;
import za.co.wethinkcode.avaj.simulator.Exceptions.UFOException;
import za.co.wethinkcode.avaj.simulator.vehicle.AircraftFactory;
import za.co.wethinkcode.avaj.simulator.vehicle.Flyable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private static  WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            int simulations = 0;
            int i = 0;


            if (line != null) {
                simulations = Integer.parseInt(line);
                weatherTower =  new WeatherTower();
                if (simulations < 0) {
                    System.out.println("Error: invalid simulation count. Simulation count must be greater or equal to 0");
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null) {
                    Flyable flyable = AircraftFactory.newAircraft(String.join("#", line.split(" ")[0], line.split(" ")[1])
                            , Integer.parseInt(line.split(" ")[2])
                            , Integer.parseInt(line.split(" ")[3])
                            , Integer.parseInt(line.split(" ")[4] ));
                    flyables.add(flyable);

                }
                for (Flyable flyable : flyables) {
                    flyable.registerTower(weatherTower);
                }

                while (i < simulations) {
                    weatherTower.changeWeather();
                    i++;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Please specify file / File invalid");
        }
        catch (IOException e) {
            System.out.println("Error: Unable to read/write to/from file.");
        }
        catch(NumberFormatException e) {
            System.out.println("Error: One of the number fields in the file are invalid");
        }
        catch (NegativeCoordinateException | HeightOutOfRangeException | UFOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                Logger.getLogger().close();
            }
            catch(IOException e) {
                System.out.println("Errpr: Something went wrong with the logger.");
            }
        }
    }
}
