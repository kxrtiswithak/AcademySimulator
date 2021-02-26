package com.sparta.eng80.controller;

import com.sparta.eng80.model.trainee.CourseType;
import com.sparta.eng80.model.trainee.Trainee;
import com.sparta.eng80.model.trainee.TraineeFactory;
import com.sparta.eng80.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class TraineeManager {
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private final TraineeFactory traineeFactory = new TraineeFactory();
    private final List<Trainee> allTrainees = new ArrayList<>();
    private final Queue<Trainee> waitingList = new LinkedBlockingDeque<>();

    public List<Trainee> randomlyGenerateTrainee(int minNumber, int maxNumber) {
        int randomVal = randomGenerator.inRange(minNumber, maxNumber);
        List<Trainee> newTrainees = new ArrayList<>();
        for (int i = 0; i < randomVal; i++) {
            Trainee trainee = traineeFactory.generateTrainee(CourseType.randomCourseType());
            newTrainees.add(trainee);
            allTrainees.add(trainee);
        }
        return newTrainees;
    }

    public Queue<Trainee> getWaitingList() {
        return waitingList;
    }

    public void addToWaitingList(List<Trainee> trainees) {
        waitingList.addAll(trainees);
    }

    public List<Trainee> getAllTrainees() {
        return allTrainees;
    }
}
