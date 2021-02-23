package com.sparta.eng80.model;

import org.junit.Test;
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
    public void addMoreThen100TraineeTest() {
        TrainingCentre trainingCenter = new TrainingCentre("Test");
        List<Trainee> list = new ArrayList<>();
        for (int i = 0; i < 110; i++) {
            list.add(new Trainee());
        }
        list = trainingCenter.acceptTrainees(list, 0, 110);
        Assertions.assertTrue(trainingCenter.getInTraining().size() == 100);
    }
}
