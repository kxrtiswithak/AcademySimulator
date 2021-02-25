package com.sparta.eng80.model;

import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.Period;
import com.sparta.eng80.util.RandomGenerator;

import java.sql.Struct;
import java.util.*;

public abstract class TrainingCentre {


    private static final long SEED = 1234987293479834781L;
    private static final RandomGenerator randomGenerator = new RandomGenerator(SEED);
    //private static int ID;
    private String name;
    public int MAX_SIZE;
    private List<Trainee> inTraining = new ArrayList<>();
    public Date openDate;
    public boolean isClosed = false;
    Hashtable<Date, Integer> monthlyUpdates = new Hashtable<>();
    public int spacesAvailable;

    public TrainingCentre(String name, Date openDate) {
        this.name = name;
        this.MAX_SIZE = 100;
        this.openDate = openDate;
    }

    public TrainingCentre(String name, Date openDate, int size){
        this.name = name;
        this.MAX_SIZE = size;
        this.spacesAvailable = MAX_SIZE;
        this.openDate = openDate;
    }

    public String getName() {
        return name;
    }

    public boolean addTrainee(Trainee trainee) {
        if (trainee == null) {
            return false;
        }
        trainee.setIsWaiting(false);
        return inTraining.add(trainee);
    }

    public Queue<Trainee> acceptTrainees(Queue<Trainee> traineeQueue, int minNumber, int maxNumber) {
        int randomVal = randomGenerator.inRange(minNumber, maxNumber);
        spacesAvailable = maxNumber - randomVal;
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

    public int getAge() {
        Period period = Period.between(openDate, Simulation.getTrainingCentreManager().getCurrentDate());
        return period.getMonths();
    }

    public void updateInfo() {
        if (!isClosed) {
            monthlyUpdates.put(Simulation.getTrainingCentreManager().getCurrentDate(), getInTraining().size());
        }
    }

    public List<Trainee> clearTrainees() {
        List<Trainee> trainees = new ArrayList<>(inTraining);
        for (Iterator<Trainee> iter = inTraining.iterator(); iter.hasNext();) {
            Trainee trainee = iter.next();
            trainee.setIsWaiting(false);
            iter.remove();
        }
//        for (Trainee trainee : inTraining) {
//            inTraining.remove(trainee);
//            trainee.setIsWaiting(true);
//        }
        return trainees;
    }
}
