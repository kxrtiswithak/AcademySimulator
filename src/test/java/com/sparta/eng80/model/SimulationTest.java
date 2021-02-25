package com.sparta.eng80.model;

import com.sparta.eng80.controller.TrainingCentreManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

public class SimulationTest {
//    Simulation simulation;
//    int numberOfMonths;
//    @BeforeEach
//    public void setup() {
//        numberOfMonths = 300;
//        simulation = new Simulation();
//        simulation.setSimulationFor(BigInteger.valueOf(numberOfMonths));
//        simulation.run();
//    }
//
//    @Test
//    public void checkCorrectNumberOfTrainingCentresCreatedAtEnd() {
//        int numberOfTrainingCentres = simulation.getTrainingCentreManager().getListOfTrainingCenters().size();
//        Assertions.assertEquals(150, numberOfTrainingCentres);
//    }
//
//    @Test
//    public void checkNumberOfTraineesInRangeAtEnd() {
//        int numberOfTrainees = simulation.getTraineeManager().getWaitingList().size();
//        List<TrainingCentre> trainingCentres = simulation.getTrainingCentreManager().getListOfTrainingCenters();
//        for (TrainingCentre techCentre : trainingCentres) {
//            numberOfTrainees += techCentre.getInTraining().size();
//        }
//        Assertions.assertTrue(numberOfTrainees >= numberOfMonths * 20 && numberOfTrainees <= numberOfMonths * 30);
//    }
//
//    @Test
//    public void checkNumberOfFullTrainingCentresLessThanTotal() {
//        List<TrainingCentre> trainingCentres = simulation.getTrainingCentreManager().getListOfTrainingCenters();
//        int numberOfFullCentres = 0, numberOfCentres;
//        numberOfCentres = trainingCentres.size();
//        for (TrainingCentre techCentre:trainingCentres) {
//            if(techCentre.getInTraining().size() == 100) {
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
//        for (TrainingCentre techCentre:trainingCentres) {
//            if(techCentre.getInTraining().size() != 100) {
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
//        for (TrainingCentre techCentre:trainingCentres) {
//            if(techCentre.getInTraining().size() == 100) {
//                numberOfFullCentres++;
//            } else {
//                numberOfOpenCentres++;
//            }
//        }
//        Assertions.assertEquals(numberOfCentres, numberOfFullCentres + numberOfOpenCentres);
//    }
//
//    @Test
//    public void checkNumberOfEachCentreIsEqualToTotal() {
//        TrainingCentreManager trainingCentreManager = simulation.getTrainingCentreManager();
//        int totalNumberOfTrainingCentres = trainingCentreManager.getListOfTrainingCenters().size();
//        int numberOfBootcamps = trainingCentreManager.getNumberOfBootcamps();
//        int numberOfTrainingHubs = trainingCentreManager.getNumberOfTrainingHub();
//        int numberOfTechCentres = trainingCentreManager.getNumberOfTechCentres();
//        Assertions.assertEquals(totalNumberOfTrainingCentres, (numberOfTechCentres+numberOfTrainingHubs+numberOfBootcamps));
//    }
//
//    @Test
//    public void getTrainingCentreAge(){
//        TrainingCentreManager trainingCentreManager = simulation.getTrainingCentreManager();
//        List<TrainingCentre> trainingCentres = trainingCentreManager.getListOfTrainingCenters();
//        for (TrainingCentre trainingCentre: trainingCentres) {
//
//        }
//        Assertions.assertEquals();
//    }
}
