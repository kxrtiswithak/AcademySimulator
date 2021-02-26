package com.sparta.eng80.controller;

import com.sparta.eng80.model.client.Client;
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
    private final Queue<Trainee> bench = new LinkedBlockingDeque<>();

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

    public void giveToTraineesToClient(Client client){
        int traineesNeeded  = client.getTraineesNeeded();
        int traineesAssigned = getNumOfTrainees();

        while(traineesNeeded != 0 || traineesAssigned != 0) {
            client.clientTrainees.add(bench.poll());
            traineesNeeded--;
            traineesAssigned--;
        }
    }

    public int getNumOfTrainees(){
        if(bench.size() <= 15){
            return bench.size();
        }else{
            return randomGenerator.inRange(1,15);
        }
    }

    public Queue<Trainee> getWaitingList() {
        return waitingList;
    }

    public Queue<Trainee> getBench() {
        return bench;
    }

    public void addToWaitingList(List<Trainee> trainees) {
        waitingList.addAll(trainees);
    }

    public void addToBench(Trainee trainee) {
            bench.add(trainee);
    }

    public List<Trainee> getAllTrainees() {
        return allTrainees;
    }
}
