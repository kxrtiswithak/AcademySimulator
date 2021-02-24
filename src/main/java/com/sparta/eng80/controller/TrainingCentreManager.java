package com.sparta.eng80.controller;

import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.model.types_of_centres.Bootcamp;
import com.sparta.eng80.model.types_of_centres.TechCentre;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingCentreManager {
    private int numberOfTrainingCentres = 0;
    private int numberOfBootcamps = 0;
    private int numberOfTechCentres = 0;
    private LocalDate startDate;
    private LocalDate currentDate;
    private LocalDate lastCentreAddedDate;
    private LocalDate lastBootcampAddedDate;
    private LocalDate lastTechCentreAddedDate;
    private ArrayList<TrainingCentre> listOfTrainingCentres = new ArrayList<>();

    public TrainingCentreManager(LocalDate startDate) {
        this.startDate = lastCentreAddedDate = startDate;
    }

    public void generateNewCentre(LocalDate currentDate) {
        String trainingCentreName = "Training Center " + numberOfTrainingCentres;
        this.currentDate = currentDate;
        if (currentDate.equals(lastCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)) {
            TrainingCentre trainingCentre = new TrainingCentre(trainingCentreName);
            numberOfTrainingCentres++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(trainingCentre);
        }
    }

    public void generateNewBootcamp(LocalDate currentDate){
        String bootcampName = "Boot Camp " + numberOfBootcamps;
        if(numberOfBootcamps < 2 || currentDate.equals(lastBootcampAddedDate.plusMonths(3)) || currentDate.equals(startDate)){
            Bootcamp bootcamp = new Bootcamp(bootcampName);
            numberOfBootcamps++;
            lastBootcampAddedDate = currentDate;
            listOfTrainingCentres.add(bootcamp);
        }
    }

    public void generateNewTechCentre(LocalDate currentDate){
        String techCentreName = "Tech Centre " + numberOfTechCentres;
        if(currentDate.equals(lastTechCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)){
            TechCentre techCentre = new TechCentre(techCentreName);
            numberOfTechCentres++;
            lastTechCentreAddedDate = currentDate;
            listOfTrainingCentres.add(techCentre);
        }
    }

    public ArrayList<TrainingCentre> getListOfTrainingCenters() {
        return listOfTrainingCentres;
    }

    public int getNumOfFullTrainingCentres() {
        int count = 0;
        for (TrainingCentre trainingCentre : listOfTrainingCentres) {
            if (trainingCentre.getSize() == TrainingCentre.MAX_SIZE) {
                count++;
            }
        }
        return count;
    }

}
