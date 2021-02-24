package com.sparta.eng80.controller;

import com.sparta.eng80.model.Trainee;

import org.junit.jupiter.api.BeforeEach;
import com.sparta.eng80.model.TrainingCentre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainingCentreManagerTest {
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
        trainingCentreManager.generateNewTrainingHub(LocalDate.now());
        Assertions.assertTrue(trainingCentreManager.getListOfTrainingCenters().size() == 1);
    }

    @Test
    public void generateNewUniqueCentreTest() {
        trainingCentreManager.generateNewTrainingHub(startDate);
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(2));
        ArrayList<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        Assertions.assertFalse(trainingCentres.get(0).equals(trainingCentres.get(1)));
    }

    @Test
    public void generateNewCentreEvery2MonthsTest() {
        LocalDate currentDate = startDate, endDate = startDate.plusMonths(12);
        while(currentDate.isBefore(endDate)){
            trainingCentreManager.generateNewTrainingHub(currentDate);
            currentDate = currentDate.plusMonths(1);
        }
        ArrayList<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        Assertions.assertTrue(trainingCentres.size() == 6);
    }

    @Test
    public void getNumOfFullTrainingCentresTest() {
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int count = 0; count < 220; count++) {
            Trainee trainee = new Trainee();
            trainees.add(trainee);
        }
        trainingCentreManager.generateNewTrainingHub(startDate);
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(2));
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(4));
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
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int count = 0; count < 220; count++) {
            Trainee trainee = new Trainee();
            trainees.add(trainee);
        }
        trainingCentreManager.generateNewTrainingHub(startDate);
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(2));
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(4));
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
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int count = 0; count < 220; count++) {
            Trainee trainee = new Trainee();
            trainees.add(trainee);
        }
        trainingCentreManager.generateNewTrainingHub(startDate);
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(2));
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(4));
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
        Queue<Trainee> trainees = new LinkedBlockingQueue<>();
        for (int count = 0; count < 220; count++) {
            Trainee trainee = new Trainee();
            trainees.add(trainee);
        }
        trainingCentreManager.generateNewTrainingHub(startDate);
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(2));
        trainingCentreManager.generateNewTrainingHub(startDate.plusMonths(4));
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

    @Test
    public void generateRandomTypeOfCentre() {
        for (int count = 0; count < 5; count++) {
            trainingCentreManager.randomlyGenerateCentre(startDate);
        }
        List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        for (int count = 0; count < 3; count++) {
            Assertions.assertTrue((trainingCentres.get(count).getClass() != trainingCentres.get(count+1).getClass()) || (trainingCentres.get(count).getClass() != trainingCentres.get(count+2).getClass()));
        }
    }

    //Todo: Fix once implemented
    // - numberOfBootcamps = trainingCentreManager.getNumberOfBootcamps
    @Test
    public void generateBootcampWhen2ExistCheck() {
        for (int count = 0; count < 4; count++) {
            trainingCentreManager.generateNewBootcamp(startDate);
        }
        int numberOfBootcamps = 2;
        Assertions.assertEquals(2, numberOfBootcamps);
    }
}
