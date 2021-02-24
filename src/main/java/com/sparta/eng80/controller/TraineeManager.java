package com.sparta.eng80.controller;

import com.sparta.eng80.model.Trainee;
import com.sparta.eng80.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TraineeManager {

    private static final long SEED = 1397819237199981723L;
    private static final RandomGenerator randomGenerator = new RandomGenerator(SEED);
    private Queue<Trainee> waitingList = new LinkedBlockingQueue<>();

    public List<Trainee> generateNewTrainees(int minNumber, int maxNumber) {
        int randomVal = randomGenerator.inRange(minNumber, maxNumber);
        List<Trainee> newTrainees = new ArrayList<>();
        for (int i = 0; i < randomVal; i++) {
            newTrainees.add(new Trainee());
        }
        return newTrainees;
    }

    public Queue<Trainee> getWaitingList() {
        return waitingList;
    }

    public void addToWaitingList(List<Trainee> trainees) {
        waitingList.addAll(trainees);
    }
}
