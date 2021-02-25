package com.sparta.eng80.model;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class DateInputTest {

    Simulation simulation = new Simulation();

    @Test
    public void inputValidMonth() {
        simulation.setSimulationFor(BigInteger.TWO);
    }

    @Test
    public void inputValidMonthAndYear() {
        simulation.setSimulationFor(BigInteger.ONE, BigInteger.TWO);
    }

    @Test
    public void inputValidMonthYearAndDay() {
        simulation.setSimulationFor(BigInteger.valueOf(-12), BigInteger.valueOf(-12), BigInteger.valueOf(-12));
    }

    @Test
    public void inputDateAfterCurrent() {
        simulation.setSimulationUntil(BigInteger.valueOf(2022), (short) 1, (short) 1);
    }

    @Test
    public void inputInvalidMonth() {
        simulation.setSimulationFor(BigInteger.valueOf(-12));
    }

    @Test
    public void inputInvalidMonthAndYear() {
        simulation.setSimulationFor(BigInteger.valueOf(-10), BigInteger.valueOf(3));
    }

    @Test
    public void inputInvalidMonthYearAndDay() {
        simulation.setSimulationFor(BigInteger.valueOf(2), BigInteger.valueOf(12), BigInteger.valueOf(-19));
    }

    @Test
    public void inputDateBeforeCurrent() {
        simulation.setSimulationUntil(BigInteger.valueOf(2020), (short) 1, (short) 1);
    }
}