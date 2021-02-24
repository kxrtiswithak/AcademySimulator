package com.sparta.eng80.model;

import com.sparta.eng80.controller.TraineeManager;
import com.sparta.eng80.util.Printer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainingCentreTest {
    TrainingCentre trainingCentre;

    @BeforeEach
    public void setup() {
        trainingCentre = new TrainingCentre("test");
    }

    @Test
    public void createTrainingCentre() {
        Assertions.assertNotNull(trainingCentre);
    }

    @Test
    public void addTraineesToTrainingCentre() {

        trainingCentre.addTrainee(new Trainee());
        Assertions.assertTrue(trainingCentre.getInTraining().size() > 0);
    }

    @Test
    public void addNullTraineeTest() {

        Assertions.assertFalse(trainingCentre.addTrainee(null));
    }

    @Test
    public void add0To20TraineesTest() {

        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;
        for (int i = 0; i < 100; i++) {
            trainees.add(new Trainee());
        }
        trainingCentre.acceptTrainees(trainees, 0, 20);
        int trainingCentreCapacity = trainingCentre.getInTraining().size();
        Assertions.assertTrue( trainingCentreCapacity >= 0 && trainingCentreCapacity <= 20);
    }

    @Test
    public void addMoreThan100TraineesTest() {

        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;

        for (int i = 0; i < 110; i++) {
            trainees.add(new Trainee());
        }
        trainingCentre.acceptTrainees(trainees, 101, 110);
        Assertions.assertTrue(trainingCentre.getInTraining().size() == 100);
    }

    @Test
    public void traineesRemovedFromWaitingListAfterAddedToCentre() {
        TrainingCentre trainingCentre = new TrainingCentre("test");
        TraineeManager traineeManager = new TraineeManager();
        List<Trainee> traineeList1 = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(traineeList1);
        Queue<Trainee> waitingList1 = traineeManager.getWaitingList();
        Queue<Trainee> waitingList2 = trainingCentre.acceptTrainees(waitingList1, 0, 20);
        List<Trainee> traineesInCentre = trainingCentre.getInTraining();
        for (Trainee trainee: traineesInCentre) {
            Assertions.assertTrue(traineesInCentre.contains(trainee));
            Assertions.assertFalse(waitingList2.contains(trainee));
        }
    }

    @Test
    public void traineesOnWaitingListAddedFIFO() {
        TraineeManager traineeManager = new TraineeManager();
        TrainingCentre trainingCentre = new TrainingCentre("test");
        List<Trainee> traineeList = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(traineeList);
        Queue<Trainee> waitingList1 = traineeManager.getWaitingList();
        trainingCentre.acceptTrainees(waitingList1, 0, 20);
        List<Trainee> traineesInCentre = trainingCentre.getInTraining();
        Assertions.assertEquals(traineeList.get(0), traineesInCentre.get(0));
    }
}
