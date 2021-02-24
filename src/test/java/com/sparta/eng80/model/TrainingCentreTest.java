package com.sparta.eng80.model;

import com.sparta.eng80.controller.TraineeManager;
import com.sparta.eng80.util.Printer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.security.Principal;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainingCentreTest {

    @Test
    public void createTrainingCentre() {
        TrainingCentre trainingCenter = new TrainingCentre("Test");
        Assertions.assertNotNull(trainingCenter);
    }

    @Test
    public void addTraineesToTrainingCentre() {
        TrainingCentre trainingCenter = new TrainingCentre("Test");
        trainingCenter.addTrainee(new Trainee());
        Assertions.assertTrue(trainingCenter.getInTraining().size() > 0);
    }

    @Test
    public void addNullTraineeTest() {
        TrainingCentre trainingCenter = new TrainingCentre("Test");
        Assertions.assertFalse(trainingCenter.addTrainee(null));
    }

    @Test
    public void add20To30TraineesTest() {
        TrainingCentre trainingCenter = new TrainingCentre("Test");
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;
        for (int i = 0; i < 100; i++) {
            trainees.add(new Trainee());
        }
        trainingCenter.acceptTrainees(trainees, 20, 30);
        int trainingCenterCapacity = trainingCenter.getInTraining().size();
        Assertions.assertTrue( trainingCenterCapacity >= 20 && trainingCenterCapacity <= 30);
    }

    @Test
    public void addMoreThan100TraineeTest() {
        TrainingCentre trainingCenter = new TrainingCentre("Test");
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;

        for (int i = 0; i < 110; i++) {
            trainees.add(new Trainee());
        }
        trainingCenter.acceptTrainees(trainees, 101, 110);
        Assertions.assertTrue(trainingCenter.getInTraining().size() == 100);
    }

    @Test
    public void traineesRemovedFromWaitingListAfterAddedToCentre() {
        TraineeManager traineeManager = new TraineeManager();
        TrainingCentre trainingCentre = new TrainingCentre("test");
        List<Trainee> traineeList = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(traineeList);
        Queue<Trainee> waitingList1 = traineeManager.getWaitingList();
        Queue<Trainee> waitingList2 = trainingCentre.acceptTrainees(waitingList1, 0, 20);
        List<Trainee> traineesInCentre = trainingCentre.getInTraining();
        Printer.printString(traineeList.size()+" "+ waitingList1.size() + " " +waitingList2.size() + " " +traineesInCentre.size());
        for (Trainee trainee: traineesInCentre) {
            Assertions.assertFalse(waitingList2.contains(trainee));
        }
        Assertions.assertEquals(waitingList2.size(), waitingList1.size());
    }
}
