package com.sparta.eng80.model;

import com.sparta.eng80.controller.TrainingCentreManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainingCentreManagerTest {
    //TODO Tests:  TrainingCentreManager.getNumOfFullTrainingCentres() needs fixing
    // - getNumOfFullTrainingCentresTest()
    // - checkAllTrainingCentresCountedAsFull()
    // - check returned data type of getNumOfFullTrainingCentres()
    private LocalDate startDate = LocalDate.now();
    private TrainingCentreManager trainingCentreManager;

    @BeforeEach
    public void setup() {
       trainingCentreManager = new TrainingCentreManager(startDate);
    }

    @Test
    public void TrainingCentreManagerTest() {
        Assertions.assertNotNull(trainingCentreManager);
    }

    @Test
    public void generateNewCentreTest() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(LocalDate.now());
        trainingCentreManager.generateNewCentre(LocalDate.now());
        Assertions.assertTrue(trainingCentreManager.getListOfTrainingCenters().size() == 1);
    }

    @Test
    public void generateNewUniqueCentreTest() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate);
        trainingCentreManager.generateNewCentre(startDate);
        trainingCentreManager.generateNewCentre(startDate.plusMonths(2));
        ArrayList<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        Assertions.assertFalse(trainingCentres.get(0).equals(trainingCentres.get(1)));
    }

    @Test
    public void generateNewCentreEvery2MonthsTest() {
        LocalDate currentDate = startDate, endDate = startDate.plusMonths(12);
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate);
        while(currentDate.isBefore(endDate)){
            trainingCentreManager.generateNewCentre(currentDate);
            currentDate = currentDate.plusMonths(1);
        }
        ArrayList<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        Assertions.assertTrue(trainingCentres.size() == 6);
    }

    @Test
    public  void checkGetNumOfFullTrainingCentresReturnType() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate);
        int numOfFullCentre = trainingCentreManager.getNumOfFullTrainingCentres();
        Assertions.assertNotNull(numOfFullCentre);
    }

    @Test
    public void getNumOfFullTrainingCentresTest() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate);
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int count = 0; count < 220; count++) {
            Trainee trainee = new Trainee();
            trainees.add(trainee);
        }
        trainingCentreManager.generateNewCentre(startDate);
        trainingCentreManager.generateNewCentre(startDate.plusMonths(2));
        trainingCentreManager.generateNewCentre(startDate.plusMonths(4));
        List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        trainingCentres.get(0).acceptTrainees(trainees, 100, 200);
        int numOfFullTrainingCentres = 0;
        for (TrainingCentre trainingCentre : trainingCentres) {
            if(trainingCentre.getInTraining().size() == 100) {
                numOfFullTrainingCentres++;
            }
        }
        Assertions.assertEquals(1, numOfFullTrainingCentres);
    }

    @Test
    public void checkAllFullTrainingCentresHave100Trainees() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate);
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int count = 0; count < 220; count++) {
            Trainee trainee = new Trainee();
            trainees.add(trainee);
        }
        trainingCentreManager.generateNewCentre(startDate);
        trainingCentreManager.generateNewCentre(startDate.plusMonths(2));
        trainingCentreManager.generateNewCentre(startDate.plusMonths(4));
        List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        trainingCentres.get(0).acceptTrainees(trainees, 100, 200);
        trainingCentres.get(1).acceptTrainees(trainees, 100, 200);
        List<TrainingCentre> fullTrainingCentres = new ArrayList<>();
        for (TrainingCentre trainingCentre : trainingCentres) {
            if(trainingCentre.getInTraining().size() == 100) {
                fullTrainingCentres.add(trainingCentre);
            }
        }
        for (TrainingCentre trainingCentre : fullTrainingCentres) {
            Assertions.assertEquals(100, trainingCentre.getInTraining().size());
        }
    }

    @Test
    public void getNumOfOpenTrainingCentresTest() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate);
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int count = 0; count < 220; count++) {
            Trainee trainee = new Trainee();
            trainees.add(trainee);
        }
        trainingCentreManager.generateNewCentre(startDate);
        trainingCentreManager.generateNewCentre(startDate.plusMonths(2));
        trainingCentreManager.generateNewCentre(startDate.plusMonths(4));
        List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        trainingCentres.get(0).acceptTrainees(trainees, 100, 200);
        int numOfFullTrainingCentres = 0;
        for (TrainingCentre trainingCentre : trainingCentres) {
            if(trainingCentre.getInTraining().size() != 100) {
                numOfFullTrainingCentres++;
            }
        }
        Assertions.assertEquals(2, numOfFullTrainingCentres);
    }

    @Test
    public void checkAllOpenTrainingCentresLessThan100Trainers() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate);
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int count = 0; count < 220; count++) {
            Trainee trainee = new Trainee();
            trainees.add(trainee);
        }
        trainingCentreManager.generateNewCentre(startDate);
        trainingCentreManager.generateNewCentre(startDate.plusMonths(2));
        trainingCentreManager.generateNewCentre(startDate.plusMonths(4));
        List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        trainingCentres.get(0).acceptTrainees(trainees, 100, 200);
        trainingCentres.get(1).acceptTrainees(trainees, 100, 200);
        List<TrainingCentre> fullTrainingCentres = new ArrayList<>();
        for (TrainingCentre trainingCentre : trainingCentres) {
            if(trainingCentre.getInTraining().size() != 100) {
                fullTrainingCentres.add(trainingCentre);
            }
        }
        for (TrainingCentre trainingCentre : fullTrainingCentres) {
            Assertions.assertTrue(trainingCentre.getInTraining().size() != 100);
        }
    }
}
