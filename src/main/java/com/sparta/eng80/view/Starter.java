package com.sparta.eng80.view;

import com.sparta.eng80.model.Simulation;

public class Starter {
    public static void start() {
        Simulation simulation = new Simulation();
        simulation.setSimulationFor(1);
        simulation.run();
    }
}
