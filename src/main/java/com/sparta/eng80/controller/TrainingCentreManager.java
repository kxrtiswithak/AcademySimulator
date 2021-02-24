package com.sparta.eng80.controller;

import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.model.types_of_centres.Bootcamp;
import com.sparta.eng80.model.types_of_centres.TechCentre;
import com.sparta.eng80.model.types_of_centres.TrainingHub;
import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.RandomGenerator;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingCentreManager {
    private int numberOfTrainingCentres = 0;
    private int numberOfBootcamps = 0;
    private int numberOfTechCentres = 0;
    private int numberOfTrainingHub = 0;
    private Date startDate;
    private Date currentDate;
    private Date lastCentreAddedDate;
    private ArrayList<TrainingCentre> listOfTrainingCentres = new ArrayList<>();
    private RandomGenerator randomGenerator = new RandomGenerator(8923478235482354823L);


    public TrainingCentreManager(Date startDate) {
        this.startDate = lastCentreAddedDate = startDate;
    }

    public void randomlyGenerateCentre(Date currentDate){
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

    public void generateNewBootcamp(Date currentDate){
        String bootcampName = "Boot Camp " + numberOfBootcamps;
        this.currentDate = currentDate;
        if(currentDate.isEqual(lastCentreAddedDate.plusMonths(BigInteger.TWO)) || currentDate.isEqual(startDate)){
            TrainingCentre bootcamp = new Bootcamp(bootcampName, currentDate);
            numberOfBootcamps++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(bootcamp);
        }
    }

    public void generateNewTechCentre(Date currentDate){
        String techCentreName = "Tech Centre " + numberOfTechCentres;
        this.currentDate = currentDate;
        if(currentDate.isEqual(lastCentreAddedDate.plusMonths(BigInteger.TWO)) || currentDate.isEqual(startDate)){
            TrainingCentre techCentre = new TechCentre(techCentreName, currentDate);
            numberOfTechCentres++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(techCentre);
        }
    }

    public void generateNewTrainingHub(Date currentDate){
        String techCentreName = "Tech Centre " + numberOfTrainingHub;
        this.currentDate = currentDate;
        if(currentDate.isEqual(lastCentreAddedDate.plusMonths(BigInteger.TWO)) || currentDate.isEqual(startDate)){
            TrainingCentre trainingHub = new TrainingHub(techCentreName, currentDate);
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

    public Date getCurrentDate(){
        return currentDate;
    }

    private void checkCentreAges(){
        for (TrainingCentre trainingCentre : listOfTrainingCentres){
            if(trainingCentre.getAge() > 2 && trainingCentre.isClosed == false
                //TODO Specify training centre type
            ){
                if(trainingCentre.getInTraining().size() < 25){
                    trainingCentre.isClosed = true;
                }
            }
        }
    }


}
