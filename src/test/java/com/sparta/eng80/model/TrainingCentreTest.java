package com.sparta.eng80.model;

import com.sparta.eng80.controller.TraineeManager;
import com.sparta.eng80.model.types_of_centres.Bootcamp;
import com.sparta.eng80.model.types_of_centres.TechCentre;
import com.sparta.eng80.model.types_of_centres.TrainingHub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainingCentreTest {
    TrainingCentre techCentre;
    TrainingCentre bootcamp;
    TrainingCentre trainingHub;
    int maxSize;

    @BeforeEach
    public void setup() {
        techCentre = new TechCentre("Tech Centre");
        bootcamp = new Bootcamp("Bootcamp");
        trainingHub = new TrainingHub("Training Hub");
    }

    @Test
    public void createTechCentre() {
        Assertions.assertNotNull(techCentre);
    }

    @Test
    public void createBootcamp() {
        Assertions.assertNotNull(bootcamp);
    }

    @Test
    public void createTrainingHub() {
        Assertions.assertNotNull(trainingHub);
    }

    @Test
    public void addTraineesToTechCentre() {
        techCentre.addTrainee(new Trainee());
        Assertions.assertTrue(techCentre.getInTraining().size() == 1);
    }

    @Test
    public void addTraineesToBootcamp() {
        bootcamp.addTrainee(new Trainee());
        Assertions.assertTrue(bootcamp.getInTraining().size() == 1);
    }

    @Test
    public void addTraineesToTrainingHub() {
        trainingHub.addTrainee(new Trainee());
        Assertions.assertTrue(trainingHub.getInTraining().size() == 1);
    }

    @Test
    public void addNullTraineeTest() {
        Assertions.assertFalse(techCentre.addTrainee(null));
    }

    @Test
    public void add0To20TraineesTechCentreTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;
        for (int i = 0; i < 100; i++) {
            trainees.add(new Trainee());
        }
        techCentre.acceptTrainees(trainees, 0, 20);
        int trainingCentreCapacity = techCentre.getInTraining().size();
        Assertions.assertTrue( trainingCentreCapacity >= 0 && trainingCentreCapacity <= 20);
    }

    @Test
    public void add0To20TraineesBootcampTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;
        for (int i = 0; i < 100; i++) {
            trainees.add(new Trainee());
        }
        bootcamp.acceptTrainees(trainees, 0, 20);
        int trainingCentreCapacity = bootcamp.getInTraining().size();
        Assertions.assertTrue( trainingCentreCapacity >= 0 && trainingCentreCapacity <= 20);
    }

    @Test
    public void add0To20TraineesTrainingHubTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;
        for (int i = 0; i < 100; i++) {
            trainees.add(new Trainee());
        }
        trainingHub.acceptTrainees(trainees, 0, 20);
        int trainingCentreCapacity = trainingHub.getInTraining().size();
        Assertions.assertTrue( trainingCentreCapacity >= 0 && trainingCentreCapacity <= 20);
    }

    @Test
    public void addMoreThanMaxSizeTraineesTechCentreTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;
        maxSize = techCentre.MAX_SIZE;
        for (int i = 0; i < (maxSize+10); i++) {
            trainees.add(new Trainee());
        }
        techCentre.acceptTrainees(trainees, maxSize, maxSize+10);
        Assertions.assertTrue(techCentre.getInTraining().size() == maxSize);
    }

    @Test
    public void addMoreThanMaxSizeTraineesBootcampTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;
        maxSize = bootcamp.MAX_SIZE;
        for (int i = 0; i < (maxSize+10); i++) {
            trainees.add(new Trainee());
        }
        bootcamp.acceptTrainees(trainees, maxSize, maxSize+10);
        Assertions.assertTrue(bootcamp.getInTraining().size() == maxSize);
    }

    @Test
    public void addMoreThanMaxSizeTraineesTrainingHubTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();;
        maxSize = trainingHub.MAX_SIZE;
        for (int i = 0; i < (maxSize+10); i++) {
            trainees.add(new Trainee());
        }
        trainingHub.acceptTrainees(trainees, maxSize, maxSize+10);
        Assertions.assertTrue(trainingHub.getInTraining().size() == maxSize);
    }

    @Test
    public void traineesRemovedFromWaitingListAfterAddedToCentre() {
        TraineeManager traineeManager = new TraineeManager();
        List<Trainee> traineeList1 = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(traineeList1);
        Queue<Trainee> waitingList1 = traineeManager.getWaitingList();
        Queue<Trainee> waitingList2 = techCentre.acceptTrainees(waitingList1, 0, 20);
        List<Trainee> traineesInCentre = techCentre.getInTraining();
        for (Trainee trainee: traineesInCentre) {
            Assertions.assertTrue(traineesInCentre.contains(trainee));
            Assertions.assertFalse(waitingList2.contains(trainee));
        }
    }

    @Test
    public void traineesOnWaitingListAddedFIFO() {
        TraineeManager traineeManager = new TraineeManager();
        List<Trainee> traineeList = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(traineeList);
        Queue<Trainee> waitingList1 = traineeManager.getWaitingList();
        techCentre.acceptTrainees(waitingList1, 0, 20);
        List<Trainee> traineesInCentre = techCentre.getInTraining();
        Assertions.assertEquals(traineeList.get(0), traineesInCentre.get(0));
    }
}
