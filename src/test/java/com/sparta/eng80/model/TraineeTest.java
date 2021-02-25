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

//    @Test
//    public void generateRandomCourseTypePerTrainee() {
//        List<Trainee> trainees = new ArrayList<>();
//        trainees.add(trainee1);
//        trainees.add(trainee2);
//        trainees.add(trainee3);
//        trainees.add(trainee4);
//        trainees.add(trainee5);
//        for (int count = 0; count < 3; count++) {
//            Assertions.assertTrue((trainees.get(count).getCourseType() != trainees.get(count+1).getCourseType()) || (trainees.get(count).getCourseType() != trainees.get(count+2).getCourseType()));
//        }
//    }

}
