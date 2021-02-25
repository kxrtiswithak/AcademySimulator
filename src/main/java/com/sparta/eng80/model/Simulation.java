package com.sparta.eng80.model;

import com.sparta.eng80.controller.OutputManager;
import com.sparta.eng80.controller.TraineeManager;
import com.sparta.eng80.controller.TrainingCentreManager;
import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.Printer;
import com.sparta.eng80.view.App;
import com.sparta.eng80.view.FileOutput;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Queue;

public class Simulation implements Runnable {

    private static TrainingCentreManager trainingCentreManager;
    private Date simulateUntil;
    private Date currentDate;
    private final TraineeManager traineeManager;
    private OutputManager outputManager;

    public Simulation() {
        simulateUntil = Date.now();
        currentDate = Date.now();
        traineeManager = new TraineeManager();
    }

    @Override
    public void run() {
        trainingCentreManager = new TrainingCentreManager(currentDate);
        if (simulateUntil.isEqual(currentDate)) {
            Printer.printString("Please set the amount of time the simulation should simulate until!");
        } else {
            boolean byMonth = App.outputTypeSelection();
            while (!currentDate.isAfter(simulateUntil) && !currentDate.isEqual(simulateUntil)) {
                trainingCentreManager.randomlyGenerateCentre(currentDate);
                trainingCentreManager.checkCentreAges();
                if(byMonth) {
                    OutputManager outputManager = new OutputManager(trainingCentreManager, traineeManager, currentDate, false);
                    outputManager.run();
                }

                List<Trainee> newTrainees = traineeManager.generateNewTrainees(20, 30);
                traineeManager.addToWaitingList(newTrainees);
                Queue<Trainee> waitingList = traineeManager.getWaitingList();
                List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();

                for (TrainingCentre trainingCentre : trainingCentres) {
                    if (waitingList.isEmpty()) break;
                    waitingList = trainingCentre.acceptTrainees(waitingList, 0, 20);
                }

                currentDate = currentDate.plusMonths(BigInteger.ONE);
            }
        }

        boolean fileOutput = App.outputSelection();
        outputManager = new OutputManager(trainingCentreManager, traineeManager, simulateUntil, fileOutput);
        outputManager.run();
    }

    public void setSimulationFor(BigInteger months) {
        if (months.compareTo(BigInteger.ZERO) < 0) {
            Printer.printString("Sorry, months to simulate must be at-least 1 or more.");
            return;
        }
        simulateUntil = simulateUntil.plusMonths(months);
    }

    public void setSimulationFor(BigInteger years, BigInteger months) {
        if (months.compareTo(BigInteger.ZERO) < 0) {
            Printer.printString("Sorry, months to simulate must be at-least 1 or more.");
            return;
        }
        if (years.compareTo(BigInteger.ZERO) < 0) {
            Printer.printString("Sorry, years to simulate must be at-least 1 or more.");
            return;
        }
        simulateUntil = simulateUntil.plusYears(years);
        simulateUntil = simulateUntil.plusMonths(months);
    }

    public void setSimulationFor(BigInteger years, BigInteger months, BigInteger days) {
        if (months.compareTo(BigInteger.ZERO) < 0) {
            Printer.printString("Sorry, months to simulate must be at-least 1 or more.");
            return;
        }
        if (years.compareTo(BigInteger.ZERO) < 0) {
            Printer.printString("Sorry, years to simulate must be at-least 1 or more.");
            return;
        }
        if (days.compareTo(BigInteger.ZERO) < 0) {
            Printer.printString("Sorry, days must be larger then 0.");
            return;
        }
        simulateUntil = simulateUntil.plusYears(years);
        simulateUntil = simulateUntil.plusMonths(months);
        simulateUntil = simulateUntil.plusDays(days);
    }

    public void setSimulationUntil(BigInteger years, short months, short days) {
        if (currentDate.getYear().compareTo(years) > 0) {
            Printer.printString("Sorry, year has to be between " + currentDate.getYear() + " and 9999.");
            return;
        }
        if (months < 1 || months > 12) {
            Printer.printString("Sorry, month has to be 1 <= n >= 12");
            return;
        }
        if (days < 1 || days > 30) {
            Printer.printString("Sorry, days cannot be negative.");
            return;
        }
        simulateUntil = Date.of(years, months, days);
        if (simulateUntil.isBefore(currentDate) || simulateUntil.isEqual(currentDate)) {
            Printer.printString("The specified date is before or equal to the current date! Simulation won't occur.");
            simulateUntil = null;
        }
    }

    public TraineeManager getTraineeManager() {
        return traineeManager;
    }

    public static TrainingCentreManager getTrainingCentreManager() {
        return trainingCentreManager;
    }

    public OutputManager getOutputManager() {
        return outputManager;
    }
}
