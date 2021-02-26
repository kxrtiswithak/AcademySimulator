package com.sparta.eng80.controller;

import com.sparta.eng80.model.Trainee;
import com.sparta.eng80.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

public class OutputManagerTest {

    private static TraineeManager traineeManager;
    private static Date startDate;
    private static OutputManager outputManager;

    @BeforeAll
    public static void setup() {
        traineeManager = new TraineeManager();
        startDate = Date.now();
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate, traineeManager);
        TraineeManager traineeManager = new TraineeManager();
        List<Trainee> newTrainees = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(newTrainees);
        Date simulateUntil = startDate;
        simulateUntil = simulateUntil.plusMonths(BigInteger.valueOf(1424));
        outputManager = new OutputManager(trainingCentreManager, traineeManager, simulateUntil, false);
        outputManager.run();
    }

    @Test
    void outputTrainingCentres() {
        Assertions.assertNotNull(outputManager.outputTrainingCentres());
    }

    @Test
    void outputTrainees() {
        Assertions.assertNotNull(outputManager.outputTrainees());
    }

    @Test
    void outputNumOfTrainees() {
        Assertions.assertNotEquals(0, outputManager.outputNumOfTrainees());
    }

    @Test
    void outputNumOfTraineesInTraining() {
        Assertions.assertEquals(0, outputManager.outputNumOfTraineesInTraining());
    }

    @Test
    void outputNumOfTraineesInWaitingList() {
        Assertions.assertNotEquals(0, outputManager.outputNumOfTraineesInWaitingList());
    }

    @Test
    void outputNumOfCentres() {
        Assertions.assertEquals(0, outputManager.outputNumOfCentres());
    }

    @Test
    void outputNumOfOpenCentres() {
        Assertions.assertEquals(0, outputManager.outputNumOfOpenCentres());
    }

    @Test
    void outputNumOfFullCentres() {
        Assertions.assertEquals(0, outputManager.outputNumOfFullCentres());
    }

    @Test
    void outputResultsTest() {
        outputManager.outputProjectResults();
    }
}
