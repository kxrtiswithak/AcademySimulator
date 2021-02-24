package com.sparta.eng80.model;

import org.junit.jupiter.api.Test;

public class DateInputTest {

    Simulation simulation = new Simulation();

    @Test
    public void inputValidMonth() {
        simulation.setSimulationFor(5);
    }

    @Test
    public void inputValidMonthAndYear() {
        simulation.setSimulationFor(1,3);
    }

    @Test
    public void inputValidMonthYearAndDay() {
        simulation.setSimulationFor(2, 12, 19);
    }

    @Test
    public void inputDateAfterCurrent() {
        simulation.setSimulationUntil(2022, 1, 1);
    }

    @Test
    public void inputInvalidMonth() {
        simulation.setSimulationFor(-10);
    }

    @Test
    public void inputInvalidMonthAndYear() {
        simulation.setSimulationFor(-10,3);
    }

    @Test
    public void inputInvalidMonthYearAndDay() {
        simulation.setSimulationFor(2, 12, -19);
    }

    @Test
    public void inputDateBeforeCurrent() {
        simulation.setSimulationUntil(2020, 1, 1);
    }
}