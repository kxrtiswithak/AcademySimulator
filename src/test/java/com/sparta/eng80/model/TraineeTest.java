package com.sparta.eng80.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class TraineeTest {

    @Test
    @DisplayName("Testing to see if unique Trainees can be created")
    public void createUniqueTraineeTest() {
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        Assertions.assertNotSame(trainee1, trainee2);
    }

}
