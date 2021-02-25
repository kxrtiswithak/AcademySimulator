package com.sparta.eng80.controller;

import com.sparta.eng80.model.Trainee;

import com.sparta.eng80.util.Date;
import org.junit.jupiter.api.BeforeEach;
import com.sparta.eng80.model.TrainingCentre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrainingCentreManagerTest {
    private Date startDate = Date.now();
    private Date firstMonth = startDate.plusMonths(BigInteger.ONE);
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
        trainingCentreManager.generateNewTrainingHub(firstMonth);
        Assertions.assertTrue(trainingCentreManager.getListOfTrainingCenters().size() == 1);
    }

    @Test
    public void generateNewUniqueCentreTest() {
        trainingCentreManager.generateNewTrainingHub(firstMonth);
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.valueOf(2)));
        ArrayList<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        Assertions.assertFalse(trainingCentres.get(0).equals(trainingCentres.get(1)));
    }

    @Test
    public void generateNewCentreEvery2MonthsTest() {
        Date currentDate = firstMonth, endDate = firstMonth.plusMonths(BigInteger.valueOf(12));
        while(currentDate.isBefore(endDate)){
            trainingCentreManager.generateNewTrainingHub(currentDate);
            currentDate = currentDate.plusMonths(BigInteger.ONE);
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
        trainingCentreManager.generateNewTrainingHub(firstMonth);
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.TWO));
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.valueOf(4)));
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
        trainingCentreManager.generateNewTrainingHub(firstMonth);
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.TWO));
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.valueOf(4)));
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
        trainingCentreManager.generateNewTrainingHub(firstMonth);
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.TWO));
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.valueOf(4)));
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
        trainingCentreManager.generateNewTrainingHub(firstMonth);
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.TWO));
        trainingCentreManager.generateNewTrainingHub(firstMonth.plusMonths(BigInteger.valueOf(4)));
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

//    @Test
//    public void generateRandomTypeOfCentre() {
//        for (int count = 0; count < 5; count++) {
//            trainingCentreManager.randomlyGenerateCentre(firstMonth);
//        }
//        List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
//        for (int count = 0; count < 3; count++) {
//            Assertions.assertTrue((trainingCentres.get(count).getClass() != trainingCentres.get(count+1).getClass()) || (trainingCentres.get(count).getClass() != trainingCentres.get(count+2).getClass()));
//        }
//    }

    @Test
    public void generateBootcampWhen2ExistCheck() {
        for (int count = 0; count < 20; count++) {
            trainingCentreManager.randomlyGenerateCentre(firstMonth);
        }
        int numberOfBootcamps = trainingCentreManager.getNumberOfBootcamps();
        Assertions.assertTrue(numberOfBootcamps <= 2);
    }

    @Test
    public void generate3TrainingHubsAtATime() {
        for (int count = 0; count < 20; count++) {
            trainingCentreManager.randomlyGenerateCentre(firstMonth);
        }
        int numberOfTrainingHubs = trainingCentreManager.getNumberOfTrainingHub();
        Assertions.assertEquals(0, numberOfTrainingHubs%3 );
    }

    @Test
    public void closeTechCentre1Month() {
        trainingCentreManager.generateNewTechCentre(firstMonth);
        trainingCentreManager.generateNewTechCentre(firstMonth);
        int numberOfTechCentres;
        numberOfTechCentres = trainingCentreManager.getNumberOfTechCentres();
        Assertions.assertEquals(2, numberOfTechCentres);
        //TODO close centre
        numberOfTechCentres = trainingCentreManager.getNumberOfTechCentres();
        Assertions.assertEquals(1, numberOfTechCentres);
    }

    @Test
    public void closeTrainingHub1Month() {
        trainingCentreManager.generateNewTechCentre(firstMonth);
        trainingCentreManager.generateNewTechCentre(firstMonth);
        int numberOfTechCentres;
        numberOfTechCentres = trainingCentreManager.getNumberOfTechCentres();
        Assertions.assertEquals(2, numberOfTechCentres);
        //TODO close centre
        numberOfTechCentres = trainingCentreManager.getNumberOfTechCentres();
        Assertions.assertEquals(1, numberOfTechCentres);
    }
    @Test
    public void closeBootcamp3Month() {
        trainingCentreManager.generateNewTechCentre(firstMonth);
        trainingCentreManager.generateNewTechCentre(firstMonth);
        int numberOfTechCentres;
        numberOfTechCentres = trainingCentreManager.getNumberOfTechCentres();
        Assertions.assertEquals(2, numberOfTechCentres);
        //TODO close centre
        numberOfTechCentres = trainingCentreManager.getNumberOfTechCentres();
        Assertions.assertEquals(1, numberOfTechCentres);
    }
}
