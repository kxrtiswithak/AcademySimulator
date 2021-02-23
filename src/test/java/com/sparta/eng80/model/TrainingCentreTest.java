package com.sparta.eng80.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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
        boolean added = true;
        for (int i = 0; i < 110; i++) {
            added = trainingCenter.addTrainee(new Trainee());
            if (!added) {
                break;
            }
        }
        Assertions.assertFalse(added);
    }
}
