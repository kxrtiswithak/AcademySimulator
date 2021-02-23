package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {

    private LocalDate simulateUntil;
    private LocalDate currentDate;
    private TraineeManager traineeManager = new TraineeManager();
    private TrainingCentreManager trainingCentreManager;

    public Simulation() {
        simulateUntil = LocalDate.now();
        currentDate = LocalDate.now();
        trainingCentreManager = new TrainingCentreManager(currentDate);
    }

    @Override
    public void run() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(currentDate);
        if (simulateUntil.isEqual(currentDate)) {
            Printer.printString("Please set the amount of time the simulation should simulate until!");
        } else {
            while (!currentDate.isAfter(simulateUntil)) {
                //...
                trainingCentreManager.generateNewCentre(currentDate);
//              Printer.printString("Day : " + currentDate.toString());
//              currentDate = currentDate.plusDays(1);
                Printer.printString("Date : " + currentDate.toString());
                currentDate = currentDate.plusMonths(1);

                List<Trainee> newTrainees = traineeManager.generateNewTrainees(20, 30);
                List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();

                for (TrainingCentre trainingCentre : trainingCentres) {
                    newTrainees = trainingCentre.acceptTrainees(newTrainees, 10, 20);
                }
            }
        }
        ArrayList<TrainingCentre> trainingCentersList = trainingCentreManager.getListOfTrainingCenters();
        Printer.printString("Size: " + trainingCentersList.size());
        for (TrainingCentre center:trainingCentersList) {
            Printer.printString("Name: " + center.getName());

        }
    }

    //These methods allow you to set an arbitrary number of years, months and days for the simulator to run until.

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

    //This method allow you to set a date for the simulator to run until

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

}
