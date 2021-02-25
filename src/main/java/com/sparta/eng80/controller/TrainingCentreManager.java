package com.sparta.eng80.controller;

import com.sparta.eng80.model.Trainee;
import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.model.types_of_centres.Bootcamp;
import com.sparta.eng80.model.types_of_centres.TechCentre;
import com.sparta.eng80.util.RandomGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            TrainingCentre bootcamp = new Bootcamp(bootcampName, currentDate);
            numberOfBootcamps++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(bootcamp);
        }
    }

    public void generateNewTechCentre(LocalDate currentDate){
        String techCentreName = "Tech Centre " + numberOfTechCentres;
        this.currentDate = currentDate;
        if(currentDate.equals(lastCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)){
            TrainingCentre techCentre = new TechCentre(techCentreName, currentDate);
            numberOfTechCentres++;
            lastCentreAddedDate = currentDate;
            listOfTrainingCentres.add(techCentre);
        }
    }

    public void generateNewTrainingHub(LocalDate currentDate){
        String techCentreName = "Tech Centre " + numberOfTrainingHub;
        this.currentDate = currentDate;
        if(currentDate.equals(lastCentreAddedDate.plusMonths(2)) || currentDate.equals(startDate)){
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
            if (trainingCentre.getSize() == trainingCentre.MAX_SIZE) {
                count++;
            }
        }
        return count;
    }

    public LocalDate getCurrentDate(){
        return currentDate;
    }

    private void checkCentreAges(){
        //For all the training centres, checks if the age is 2 months old and the centre is open
        for (TrainingCentre trainingCentre : listOfTrainingCentres){
            if(trainingCentre.getAge() == 2 && !trainingCentre.isClosed &&
                //TODO Check if this works
                    trainingCentre.getClass().getName().equals("Bootcamp") ){
                //If so the size is checked. If below 25 the centre is closed and trainees are reallocated
                if(trainingCentre.getInTraining().size() < 25){
                    trainingCentre.isClosed = true;
                    List<Trainee> trainees = trainingCentre.clearTrainees();
                    reallocateTraineesRandomly(trainees);
                }
            }
        }
    }

    private void updateTrainingCentres(){
        for (TrainingCentre trainingCentre : listOfTrainingCentres){
            trainingCentre.updateInfo();
        }
    }

    //TODO needs a way of allocating the trainees based on their subject
    private void reallocateTrainees(List<Trainee> trainees){
        for (TrainingCentre trainingCentre : listOfTrainingCentres){
            if(trainingCentre.getInTraining().size() == trainingCentre.MAX_SIZE|| trainingCentre.isClosed){
                continue;
            } else {
                for (int i = 0; i< trainingCentre.spacesAvailable; i++){
                    if (trainingCentre.getInTraining().size() < trainingCentre.MAX_SIZE) {  //redundant?
                        Trainee trainee = trainees.remove(i);
                        trainingCentre.addTrainee(trainee);
                    }
                }
            }
        }
        //TODO Where to add extra trainees if all centres are full
    }

    private void reallocateTraineesRandomly(List<Trainee> trainees){
        List<TrainingCentre> availableCentres = new ArrayList<>();
        for (TrainingCentre trainingCentre : listOfTrainingCentres){
            if(trainingCentre.getInTraining().size() != trainingCentre.MAX_SIZE|| !trainingCentre.isClosed){
                availableCentres.add(trainingCentre);
            }
        }
        while (availableCentres.size() > 0 && trainees.size()>0){
            int centreSelection = randomGenerator.inRange(0, availableCentres.size());
            for (int i = 0; i< availableCentres.get(centreSelection).spacesAvailable; i++){
                if (availableCentres.get(centreSelection).getInTraining().size() < availableCentres.get(centreSelection).MAX_SIZE) {  //redundant?
                    Trainee trainee = trainees.remove(i);
                    availableCentres.get(centreSelection).addTrainee(trainee);
                }
            }
            availableCentres.remove(centreSelection);
        }
    }

}
