package com.sparta.eng80.controller;

import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.model.types_of_centres.Bootcamp;
import com.sparta.eng80.model.types_of_centres.TechCentre;
import com.sparta.eng80.model.types_of_centres.TrainingHub;
import com.sparta.eng80.util.RandomGenerator;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingCentreManager {
    private int numberOfTrainingCentres = 0;
    private int numberOfBootcamps = 0;
    private int numberOfTechCentres = 0;
    private int numberOfTrainingHub = 0;
    private LocalDate startDate;
    private LocalDate currentDate;
    private LocalDate lastCentreAddedDate;
    private ArrayList<TrainingCentre> listOfTrainingCentres = new ArrayList<>();
    private RandomGenerator randomGenerator = new RandomGenerator(8923478235482354823L);


    public TrainingCentreManager(LocalDate startDate) {
        this.startDate = lastCentreAddedDate = startDate;
    }

    public void randomlyGenerateCentre(LocalDate currentDate){
        int num1 = numberOfBootcamps < 2 ? 1 : 2;
        int choice = randomGenerator.inRange(num1,3);
        switch(choice){
            case 1:
                generateNewBootcamp(currentDate);
                break;
            case 2:
                generateNewTechCentre(currentDate);
                break;
            case 3:
                generateNewTrainingHub(currentDate);
                break;
        }

    }

    public void generateNewBootcamp(LocalDate currentDate){
        String bootcampName = "Boot Camp " + numberOfBootcamps;
        this.currentDate = currentDate;
        if(currentDate.equals(lastCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)){
            TrainingCentre bootcamp = new Bootcamp(bootcampName);
            numberOfBootcamps++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(bootcamp);
        }
    }

    public void generateNewTechCentre(LocalDate currentDate){
        String techCentreName = "Tech Centre " + numberOfTechCentres;
        this.currentDate = currentDate;
        if(currentDate.equals(lastCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)){
            TrainingCentre techCentre = new TechCentre(techCentreName);
            numberOfTechCentres++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(techCentre);
        }
    }

    public void generateNewTrainingHub(LocalDate currentDate){
        String techCentreName = "Tech Centre " + numberOfTrainingHub;
        this.currentDate = currentDate;
        if(currentDate.equals(lastCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)){
            TrainingCentre trainingHub = new TrainingHub(techCentreName);
            numberOfTrainingHub++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(trainingHub);
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
