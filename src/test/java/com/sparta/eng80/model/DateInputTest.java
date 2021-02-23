package com.sparta.eng80.model;

import com.sparta.eng80.model.Simulation;
import org.junit.Test;

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

    @Test
    public void inputDateAfterCurrent() {
        Simulation simulation = new Simulation();
        simulation.setSimulationUntil(2022, 1, 1);
    }

    @Test
    public void inputInvalidMonth() {
        Simulation simulation = new Simulation();
        simulation.setSimulationFor(-10);
    }

    @Test
    public void inputInvalidMonthAndYear() {
        Simulation simulation = new Simulation();
        simulation.setSimulationFor(-10,3);
    }

    @Test
    public void inputInvalidMonthYearAndDay() {
        Simulation simulation = new Simulation();
        simulation.setSimulationFor(2, 12, -19);
    }

    @Test
    public void inputDateBeforeCurrent() {
        Simulation simulation = new Simulation();
        simulation.setSimulationUntil(2020, 1, 1);
    }
}