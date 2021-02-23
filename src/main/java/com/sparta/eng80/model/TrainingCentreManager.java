package com.sparta.eng80.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingCentreManager {
    private int numberOfTrainingCentres = 0;
    private LocalDate startDate;
    private LocalDate currentDate;
    private LocalDate lastCentreAddedDate;
    private ArrayList<TrainingCentre> listOfTrainingCentres = new ArrayList<>();

    TrainingCentreManager(LocalDate startDate) {
        this.startDate = lastCentreAddedDate = startDate;
    }

    public void generateNewCentre(LocalDate currentDate) {
        String trainingCentreName = "Training Center " + numberOfTrainingCentres;
        this.currentDate = currentDate;
//        Printer.printString("Current: " + currentDate + " last: " + lastCentreAddedDate);
        if (currentDate.equals(lastCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)) {
            TrainingCentre trainingCentre = new TrainingCentre(trainingCentreName);
            numberOfTrainingCentres++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(trainingCentre);
//            Printer.printString("New Training Center added: " + trainingCenter.getName() + " On " + currentDate);
        }
    }

    public ArrayList<TrainingCentre> getListOfTrainingCenters() {
        return listOfTrainingCentres;
    }

}
