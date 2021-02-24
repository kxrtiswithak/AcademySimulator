package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

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
        List<Trainee> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Trainee());
        }
        trainingCenter.acceptTrainees(list, 20, 30);
        int trainingCenterCapacity = trainingCenter.getInTraining().size();
        Assertions.assertTrue( trainingCenterCapacity >= 20 && trainingCenterCapacity <= 30);
    }

    @Test
    public void addMoreThan100TraineeTest() {
        TrainingCentre trainingCenter = new TrainingCentre("Test");
        List<Trainee> list = new ArrayList<>();
        for (int i = 0; i < 110; i++) {
            list.add(new Trainee());
        }
        trainingCenter.acceptTrainees(list, 101, 110);
        Assertions.assertTrue(trainingCenter.getInTraining().size() == 100);
    }
}
