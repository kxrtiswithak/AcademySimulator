package com.sparta.eng80.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TraineeTest {

    @Test
    public void createNewTraineeTest() {
        Trainee trainee = new Trainee();
        Assertions.assertNotNull(trainee);
    }

    @Test
    public void createUniqueTraineeTest() {
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        Assertions.assertNotSame(trainee1, trainee2);
    }

}
