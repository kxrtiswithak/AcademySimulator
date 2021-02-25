package com.sparta.eng80.model;

import com.sparta.eng80.view.App;
import com.sparta.eng80.controller.OutputManager;
import com.sparta.eng80.controller.TraineeManager;
import com.sparta.eng80.controller.TrainingCentreManager;
import com.sparta.eng80.util.Printer;

import java.time.LocalDate;
import java.util.List;
import java.util.Queue;

public class Simulation implements Runnable {

    private LocalDate simulateUntil;
    private LocalDate currentDate;
    private TraineeManager traineeManager;
    private TrainingCentreManager trainingCentreManager;
    private OutputManager outputManager;

    public Simulation() {
        simulateUntil = LocalDate.now();
        currentDate = LocalDate.now();
        traineeManager = new TraineeManager();
    }

    @Override
    public void run() {
        trainingCentreManager = new TrainingCentreManager(currentDate);
        if (simulateUntil.isEqual(currentDate)) {
            Printer.printString("Please set the amount of time the simulation should simulate until!");
        } else {
            while (!currentDate.isAfter(simulateUntil) && !currentDate.isEqual(simulateUntil)) {
                trainingCentreManager.randomlyGenerateCentre(currentDate);

                List<Trainee> newTrainees = traineeManager.generateNewTrainees(20, 30);
                traineeManager.addToWaitingList(newTrainees);
                Queue<Trainee> waitingList = traineeManager.getWaitingList();
                List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();

                for (TrainingCentre trainingCentre : trainingCentres) {
                    if (waitingList.isEmpty()) break;
                    waitingList = trainingCentre.acceptTrainees(waitingList, 0, 20);
                }

                currentDate = currentDate.plusMonths(1);
            }
        }

        boolean fileOutput = App.outputSelection();
        outputManager = new OutputManager(trainingCentreManager, traineeManager, simulateUntil, fileOutput);
        outputManager.run();
    }

    public void setSimulationFor(int months) {
        if (months < 0) {
            Printer.printString("Sorry, months to simulate must be at-least 1 or more.");
            return;
        }
        simulateUntil = simulateUntil.plusMonths(months);
    }

    public void setSimulationFor(int years, int months) {
        if (months < 0) {
            Printer.printString("Sorry, months to simulate must be at-least 1 or more.");
            return;
        }
        if (years > (9999 - currentDate.getYear()) || years < 0) {
            Printer.printString("Sorry, years cannot exceed " + (9999 - currentDate.getYear()) + " nor can it be less then 0.");
            return;
        }
        simulateUntil = simulateUntil.plusYears(years);
        simulateUntil = simulateUntil.plusMonths(months);
    }

    public void setSimulationFor(int years, int months, int days) {
        if (months < 0) {
            Printer.printString("Sorry, months to simulate must be at-least 1 or more.");
            return;
        }
        if (years > (9999 - currentDate.getYear()) || years < 0) {
            Printer.printString("Sorry, years cannot exceed " + (9999 - currentDate.getYear()) + " nor can it be less then 0.");
            return;
        }
        if (days < 0) {
            Printer.printString("Sorry, days must be larger then 0.");
            return;
        }
        simulateUntil = simulateUntil.plusYears(years);
        simulateUntil = simulateUntil.plusMonths(months);
        simulateUntil = simulateUntil.plusDays(days);
    }

    public void setSimulationUntil(int years, int months, int days) {
        if (years > 9999 || currentDate.getYear() > years) {
            Printer.printString("Sorry, year has to be between " + currentDate.getYear() + " and 9999.");
            return;
        }
        if (months < 1 || months > 12) {
            Printer.printString("Sorry, month has to be 1 <= n >= 12");
            return;
        }
        if (days < 0) {
            Printer.printString("Sorry, days cannot be negative.");
            return;
        }
        simulateUntil = LocalDate.of(years, months, days);
        if (simulateUntil.isBefore(currentDate) || simulateUntil.isEqual(currentDate)) {
            Printer.printString("The specified date is before or equal to the current date! Simulation won't occur.");
            simulateUntil = null;
        }
    }

    public TraineeManager getTraineeManager() {
        return traineeManager;
    }

    public TrainingCentreManager getTrainingCentreManager() {
        return trainingCentreManager;
    }

    public OutputManager getOutputManager() {
        return outputManager;
    }
}
