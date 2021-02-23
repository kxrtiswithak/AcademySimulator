package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TraineeManagerTest {
    @Test
    public void generateBetween20And30Trainees() {
        TraineeManager traineeManager = new TraineeManager();
        int min = 20;
        int max = 30;
        List<List<Trainee>> listOflistsOfTrainees = new ArrayList<>();
        for (int count = 0; count < 5; count++) {
            List<Trainee> listOfTrainees = traineeManager.generateNewTrainees(min, max);
            listOflistsOfTrainees.add(listOfTrainees);
        }
        for (List<Trainee> i : listOflistsOfTrainees) {
            Assertions.assertTrue(i.size() >= 20 && i.size() <= 30);
        }
    }

    @Test
    public void Test2() {

    }
}
