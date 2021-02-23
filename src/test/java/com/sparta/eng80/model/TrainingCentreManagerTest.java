package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingCentreManagerTest {

    @Test
    public void TrainingCentreManagerTest() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(LocalDate.now());
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
        LocalDate startDate = LocalDate.now();
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(startDate);
        trainingCentreManager.generateNewCentre(startDate);
        trainingCentreManager.generateNewCentre(startDate.plusMonths(2));
        ArrayList<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
        Assertions.assertFalse(trainingCentres.get(0).equals(trainingCentres.get(1)));
    }

//    @Test
//    public void getNumOfFullTrainingCentresTest() {
//        Simulation simulation = new Simulation();
//        simulation.setSimulationFor(10);
//        simulation.run();
//        int i = trainingCentreManager.getNumOfFullTrainingCentres();
//        Printer.printString(""+i);
//    }

    @Test
    public void checkAllTrainingCentresCountedAsFull() {

    }
}
