package za.co.wethinkcode.avaj.simulator;

import za.co.wethinkcode.avaj.simulator.vehicle.Flyable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    private static List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
            observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() throws IOException {

        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
