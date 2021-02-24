package com.sparta.eng80.model;

import com.sparta.eng80.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TrainingCentre {

    public static final int MAX_SIZE = 100;
    private static final long SEED = 1234987293479834781L;
    private static final RandomGenerator randomGenerator = new RandomGenerator(SEED);
    //private static int ID;
    private String name;
    private int size;
    private List<Trainee> inTraining = new ArrayList<>();

    public TrainingCentre(String name) {
        this.name = name;
        this.size = 0;
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

}
