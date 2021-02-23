package com.sparta.eng80;

import com.sparta.eng80.model.Simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

public class DateInputTest {
    Simulation simulation;
    @BeforeEach
    public void setup() {
    simulation = new Simulation();
    }

    @Test
    public void inputNotValidMonth() {
        simulation.setSimulationFor(0);
        simulation.run();
    }

    @Test
    public void inputNotValidMonthAndYear() {
        simulation.setSimulationFor(1,3);
        simulation.run();
    }

    @Test
    public void inputNotValidMonthYearAndDay() {
        simulation.setSimulationFor(2, 12, 19);
        simulation.run();
    }

    @Test
    public void inputDateBeforeCurrent() {
        simulation.setSimulationUntil(2020, 01,01);
        simulation.run();
    }

    @Test
    public void inputValidMonth() {
        simulation.setSimulationFor(0);
        simulation.run();
    }

    @Test
    public void inputValidMonthAndYear() {
        simulation.setSimulationFor(1,3);
        simulation.run();
    }

    @Test
    public void inputValidMonthYearAndDay() {
        simulation.setSimulationFor(2, 12, 19);
        simulation.run();
    }

    @Test
    public void inputDateAfterCurrent() {
        simulation.setSimulationUntil(2020, 01,01);
        simulation.run();
    }
}
