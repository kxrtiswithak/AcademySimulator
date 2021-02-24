package com.sparta.eng80.model;

import com.sparta.eng80.controller.TrainingCentreManager;
import com.sparta.eng80.util.RandomGenerator;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public abstract class TrainingCentre {


    private static final long SEED = 1234987293479834781L;
    private static final RandomGenerator randomGenerator = new RandomGenerator(SEED);
    //private static int ID;
    private String name;
    private int size;
    public static int MAX_SIZE;
    private List<Trainee> inTraining = new ArrayList<>();
    public LocalDate openDate;
    public TrainingCentreManager trainingCentreManager;
    public boolean isClosed = false;
//    List monthUpdate = new ArrayList<>();

    public TrainingCentre(String name, LocalDate openDate) {
        this.name = name;
        this.size = 0;
        this.MAX_SIZE = 100;
        this.openDate = openDate;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean addTrainee(Trainee trainee) {
        if (trainee == null) {
            return false;
        }
        return inTraining.add(trainee);
    }

    public Queue<Trainee> acceptTrainees(Queue<Trainee> traineeQueue, int minNumber, int maxNumber) {
        int randomVal = randomGenerator.inRange(minNumber, maxNumber);
        for (int i = 0; i < randomVal; i++) {
            if (!traineeQueue.isEmpty()) {
                if (inTraining.size() < MAX_SIZE) {
                    Trainee trainee = traineeQueue.remove();
                    addTrainee(trainee);
                } else {
                    break;
                }
            }
        }
        return traineeQueue;
    }

    public List<Trainee> getInTraining() {
        return inTraining;
    }

    public int getAge(){
        Period period = Period.between(openDate, trainingCentreManager.getCurrentDate());
        return period.getMonths();
    }

//    public void updateInfo(){
//        if(isClosed!=true){
//            int[] thisMonth = {getAge(),getInTraining().size()};
//            monthUpdate.add(getInTraining().size());
//        }
//    }

}
