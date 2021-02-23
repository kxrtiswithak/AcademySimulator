package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;

import java.util.ArrayList;
import java.util.List;
import com.sparta.eng80.controller.RandomGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainingCenter {

    private static final long SEED = 1234987293479834781L;
    private static final RandomGenerator randomGenerator = new RandomGenerator(SEED);

    //private static int ID;
    private String name;
    private List<Trainee> inTraining;

    public TrainingCenter(String name) {
        this.name = name;
        this.inTraining = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return getInTraining().size();
    }

    public boolean addTrainee(Trainee trainee) {
        return inTraining.add(trainee);
    }

    public List<Trainee> acceptTrainees(List<Trainee> trainees, int minNumber, int maxNumber) {
        int randomVal = randomGenerator.inRange(minNumber, maxNumber);
        Queue<Trainee> traineeQueue = new LinkedBlockingQueue<>(trainees);
        for (int i = 0; i < randomVal; i++)
        {
            if (!traineeQueue.isEmpty())
            {
                Trainee trainee = traineeQueue.remove();
                addTrainee(trainee);
            }
        }
        return new ArrayList<>(traineeQueue);
    }

    public List<Trainee> getInTraining() {
        return inTraining;
    }

    public boolean addToTrainingCentre(Trainee trainee) {
        if (trainee == null) {
            Printer.printString("Null trainee cannot be added, sorry");
            return false;
        } else if (inTraining.contains(trainee)) {
            Printer.printString("Trainee already exists, so wont be added");
            return false;
        } else if (getSize() == 100) {
            Printer.printString("Training Centre is full, please add to a different one.");
            return false;
        }
        inTraining.add(trainee);
        return true;
    }
}
