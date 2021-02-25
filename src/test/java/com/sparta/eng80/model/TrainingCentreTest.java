package com.sparta.eng80.model;

import com.sparta.eng80.controller.TraineeManager;
import com.sparta.eng80.model.types_of_centres.Bootcamp;
import com.sparta.eng80.model.types_of_centres.TechCentre;
import com.sparta.eng80.model.types_of_centres.TrainingHub;
import com.sparta.eng80.util.Date;
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
    Date startDate;

    @BeforeEach
    public void setup() {
        startDate = Date.now();
        techCentre = new TechCentre("Tech Centre", startDate);
        bootcamp = new Bootcamp("Bootcamp", startDate);
        trainingHub = new TrainingHub("Training Hub", startDate);
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
    public void createUniqueTechCentre() {
       TrainingCentre techCentre2 = new TechCentre("Tech Centre 2",startDate);
        Assertions.assertNotEquals(techCentre2, techCentre);
    }

    @Test
    public void createUniqueBootcamp() {
        TrainingCentre bootcamp2 = new Bootcamp("Bootcamp 2",startDate);
        Assertions.assertNotEquals(bootcamp2, techCentre);
    }

    @Test
    public void createUniqueTrainingHub() {
        TrainingCentre trainingHub2 = new TechCentre("Training Hub 2",startDate);
        Assertions.assertNotEquals(trainingHub2, techCentre);
    }

    @Test
    public void addTraineesToTechCentre() {
        techCentre.addTrainee(new Trainee());
        Assertions.assertEquals(techCentre.getInTraining().size(), 1);
    }

    @Test
    public void addTraineesToBootcamp() {
        bootcamp.addTrainee(new Trainee());
        Assertions.assertEquals(bootcamp.getInTraining().size(), 1);
    }

    @Test
    public void addTraineesToTrainingHub() {
        trainingHub.addTrainee(new Trainee());
        Assertions.assertEquals(trainingHub.getInTraining().size(), 1);
    }

    @Test
    public void addNullTraineeTest() {
        Assertions.assertFalse(techCentre.addTrainee(null));
    }

    @Test
    public void add0To20TraineesTechCentreTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int i = 0; i < 100; i++) {
            trainees.add(new Trainee());
        }
        techCentre.acceptTrainees(trainees, 0, 20);
        int trainingCentreCapacity = techCentre.getInTraining().size();
        Assertions.assertTrue( trainingCentreCapacity >= 0 && trainingCentreCapacity <= 20);
    }

    @Test
    public void add0To20TraineesBootcampTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int i = 0; i < 100; i++) {
            trainees.add(new Trainee());
        }
        bootcamp.acceptTrainees(trainees, 0, 20);
        int trainingCentreCapacity = bootcamp.getInTraining().size();
        Assertions.assertTrue( trainingCentreCapacity >= 0 && trainingCentreCapacity <= 20);
    }

    @Test
    public void add0To20TraineesTrainingHubTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int i = 0; i < 100; i++) {
            trainees.add(new Trainee());
        }
        trainingHub.acceptTrainees(trainees, 0, 20);
        int trainingCentreCapacity = trainingHub.getInTraining().size();
        Assertions.assertTrue( trainingCentreCapacity >= 0 && trainingCentreCapacity <= 20);
    }

    @Test
    public void getMaxSizeTechCentre() {
        Assertions.assertEquals(200, techCentre.MAX_SIZE);
    }

    @Test
    public void getMaxSizeBootcamp(){
        Assertions.assertEquals(500, bootcamp.MAX_SIZE);
    }

    @Test
    public void getMaxSizeTrainingCentre(){
        Assertions.assertEquals(100, trainingHub.MAX_SIZE);
    }

    @Test
    public void addMoreThanMaxSizeTraineesTechCentreTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        int maxSize = techCentre.MAX_SIZE;
        for (int i = 0; i < (maxSize+10); i++) {
            trainees.add(new Trainee());
        }
        techCentre.acceptTrainees(trainees, maxSize, maxSize+10);
        Assertions.assertEquals(maxSize, techCentre.getInTraining().size());
    }

    @Test
    public void addMoreThanMaxSizeTraineesBootcampTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        int maxSize = bootcamp.MAX_SIZE;
        for (int i = 0; i < (maxSize+10); i++) {
            trainees.add(new Trainee());
        }
        bootcamp.acceptTrainees(trainees, maxSize, maxSize+10);
        Assertions.assertEquals(maxSize, bootcamp.getInTraining().size());
    }

    @Test
    public void addMoreThanMaxSizeTraineesTrainingHubTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        int maxSize = trainingHub.MAX_SIZE;
        for (int i = 0; i < (maxSize+10); i++) {
            trainees.add(new Trainee());
        }
        trainingHub.acceptTrainees(trainees, maxSize, maxSize+10);
        Assertions.assertEquals(maxSize, trainingHub.getInTraining().size());
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

    @Test
    public void getTrainingCentreAge(){

    }

    @Test
    public void moveTraineesToNewCentreOnClosure() {}

    @Test
    public void moveTraineesToWaitingListOnClosure() {}

    @Test
    public void assignCourseToTechCentre() {

    }

    @Test
    public void checkJavaTechCentreTakesJavaTrainees() {}

    @Test
    public void checkCTechCentreTakesCTrainees() {}

    @Test
    public void checkDataTechCentreTakesDataTrainees() {}

    @Test
    public void checkDevOpsTechCentreTakesDevOpsTrainees() {}

    @Test
    public void checkBusinessTechCentreTakesBusinessTrainees() {}
}
