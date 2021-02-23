package com.sparta.eng80;

import com.sparta.eng80.model.Simulation;
import org.junit.jupiter.api.Test;

public class DateInputTest {
    @Test
    public void inputValidMonth() {
        Simulation simulation = new Simulation();
        simulation.setSimulationFor(5);
    }

    @Test
    public void inputValidMonthAndYear() {
        Simulation simulation = new Simulation();
        simulation.setSimulationFor(1,3);
    }

    @Test
    public void inputValidMonthYearAndDay() {
        Simulation simulation = new Simulation();
        simulation.setSimulationFor(2, 12, 19);
    }
}
