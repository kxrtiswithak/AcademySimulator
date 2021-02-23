package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingCenterManager {
    private int numberOfTrainingCentres = 0;
    private LocalDate startDate;
    private LocalDate currentDate;
    private LocalDate lastCentreAddedDate;
    private ArrayList<TrainingCenter> listOfTrainingCenters = new ArrayList<>();

    TrainingCenterManager(LocalDate startDate) {
        this.startDate = lastCentreAddedDate = startDate;
    }

    public void generateNewCentre(LocalDate currentDate) {
        String trainingCentreName = "Training Center " + numberOfTrainingCentres;
        this.currentDate = currentDate;
//        Printer.printString("Current: " + currentDate + " last: " + lastCentreAddedDate);
        if (currentDate.equals(lastCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)) {
            TrainingCenter trainingCenter = new TrainingCenter(trainingCentreName);
            numberOfTrainingCentres++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCenters.add(trainingCenter);
//            Printer.printString("New Training Center added: " + trainingCenter.getName() + " On " + currentDate);
        }
    }

    public ArrayList<TrainingCenter> getListOfTrainingCenters() {
        return listOfTrainingCenters;
    }

}
