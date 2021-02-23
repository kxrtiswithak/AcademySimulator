package com.sparta.eng80.model;

import java.util.List;
import com.sparta.eng80.controller.RandomGenerator;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainingCentre {

    private static final long SEED = 1234987293479834781L;
    private static final RandomGenerator randomGenerator = new RandomGenerator(SEED);
    public static final int MAX_SIZE = 100;

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
        if (trainee == null)
        {
            return false;
        }
        return inTraining.add(trainee);
    }

    public List<Trainee> acceptTrainees(List<Trainee> trainees, int minNumber, int maxNumber) {
        int randomVal = randomGenerator.inRange(minNumber, maxNumber);
        Queue<Trainee> traineeQueue = new LinkedBlockingQueue<>(trainees);
        for (int i = 0; i < randomVal; i++) {
            if (!traineeQueue.isEmpty())
            {
                Trainee trainee = traineeQueue.remove();
                if (inTraining.size() < MAX_SIZE)
                {
                    addTrainee(trainee);
                }
                else
                {
                    break;
                }
            }
        }
        return new ArrayList<>(traineeQueue);
    }

    public List<Trainee> getInTraining() {
        return inTraining;
    }

    public int getNumOfTrainees() {
        return inTraining.size();
    }
}