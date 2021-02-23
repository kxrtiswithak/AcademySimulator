package com.sparta.eng80.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class TrainingCentreTest {

    @Test
    @DisplayName("Testing to see if unique Trainees can be created")
    public void createTrainingCentre() {
        TrainingCentre trainingCentre = new TrainingCentre("Test");
        Assertions.assertNotNull(trainingCentre);
    }

    @Test
    @DisplayName("Testing to see if trainees can be added")
    public void addTraineesToTrainingCentre() {
        TrainingCentre trainingCentre = new TrainingCentre("Test");
        trainingCentre.addTrainee(new Trainee());
        Assertions.assertTrue(trainingCentre.getInTraining().size() > 0);
    }

    @Test
    @DisplayName("Testing to see if null trainees can be added")
    public void addNullTraineeTest() {
        TrainingCentre trainingCentre = new TrainingCentre("Test");
        Assertions.assertFalse(trainingCentre.addTrainee(null));
    }

    @Test
    @DisplayName("Testing to see if duplicate trainees can be added")
    public void addDuplicateTraineeTest() {
        TrainingCentre trainingCentre = new TrainingCentre("Test");
        Trainee trainee = new Trainee();
        trainingCentre.addTrainee(trainee);
        Assertions.assertFalse(trainingCentre.addTrainee(trainee));
    }

    @Test
    @DisplayName("Testing to see if more then 100 trainees can be added")
    public void addMoreThen100TraineeTest() {
        TrainingCentre trainingCentre = new TrainingCentre("Test");
        boolean added = true;
        for (int i = 0; i < 110; i++) {
            added = trainingCentre.addTrainee(
                    new Trainee()
            );
            if (!added) {
                break;
            }
        }
        Assertions.assertFalse(added);
    }
}
