package com.sparta.eng80.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SimulationTest {
    Simulation simulation;
    int numberOfMonths;
//    @BeforeEach
//    public void setup() {
//        numberOfMonths = 5;
//        simulation = new Simulation();
//        simulation.setSimulationFor(numberOfMonths);
//        simulation.run();
//    }
//
//    @Test
//    public void checkCorrectNumberOfTrainingCentresCreatedAtEnd() {
//        int numberOfTrainingCentres = simulation.getTrainingCentreManager().getListOfTrainingCenters().size();
//        Assertions.assertEquals(3, numberOfTrainingCentres);
//    }
//
//    @Test
//    public void checkNumberOfTraineesInRangeAtEnd() {
//        int numberOfTrainees = simulation.getTraineeManager().getWaitingList().size();
//        List<TrainingCentre> trainingCentres = simulation.getTrainingCentreManager().getListOfTrainingCenters();
//        for (TrainingCentre trainingCentre : trainingCentres) {
//            numberOfTrainees += trainingCentre.getInTraining().size();
//        }
//        Assertions.assertTrue(numberOfTrainees >= numberOfMonths * 20 && numberOfTrainees <= numberOfMonths * 30);
//    }
//
//    @Test
//    public void checkNumberOfFullTrainingCentresLessThanTotal() {
//        List<TrainingCentre> trainingCentres = simulation.getTrainingCentreManager().getListOfTrainingCenters();
//        int numberOfFullCentres = 0, numberOfCentres;
//        numberOfCentres = trainingCentres.size();
//        for (TrainingCentre trainingCentre:trainingCentres) {
//            if(trainingCentre.getInTraining().size() == 100) {
//                numberOfFullCentres++;
//            }
//        }
//        Assertions.assertTrue(numberOfFullCentres <= numberOfCentres);
//    }
//
//    @Test
//    public void checkNumberOfOpenTrainingCentresLessThanTotal() {
//        List<TrainingCentre> trainingCentres = simulation.getTrainingCentreManager().getListOfTrainingCenters();
//        int numberOfOpenCentres = 0, numberOfCentres;
//        numberOfCentres = trainingCentres.size();
//        for (TrainingCentre trainingCentre:trainingCentres) {
//            if(trainingCentre.getInTraining().size() != 100) {
//                numberOfOpenCentres++;
//            }
//        }
//        Assertions.assertTrue(numberOfOpenCentres <= numberOfCentres);
//    }
//
//    @Test
//    public void checkNumberOfFullCentresAndOpenTotal() {
//        List<TrainingCentre> trainingCentres = simulation.getTrainingCentreManager().getListOfTrainingCenters();
//        int numberOfFullCentres = 0, numberOfOpenCentres = 0, numberOfCentres;
//        numberOfCentres = trainingCentres.size();
//        for (TrainingCentre trainingCentre:trainingCentres) {
//            if(trainingCentre.getInTraining().size() == 100) {
//                numberOfFullCentres++;
//            } else {
//                numberOfOpenCentres++;
//            }
//        }
//        Assertions.assertTrue(numberOfFullCentres+numberOfOpenCentres == numberOfCentres);
//    }
}
