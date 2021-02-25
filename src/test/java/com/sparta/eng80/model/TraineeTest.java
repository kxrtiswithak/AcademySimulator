package com.sparta.eng80.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TraineeTest {
    Trainee trainee1;
    Trainee trainee2;
    Trainee trainee3;
    Trainee trainee4;
    Trainee trainee5;

    @BeforeEach
    public void setup() {
        trainee1 = new Trainee();
        trainee2 = new Trainee();
        trainee3 = new Trainee();
        trainee4 = new Trainee();
        trainee5 = new Trainee();
    }

    @Test
    public void createNewTraineeTest() {
        Assertions.assertNotNull(trainee1);
    }

    @Test
    public void createUniqueTraineeTest() {
        Assertions.assertNotSame(trainee1, trainee2);
    }

}
