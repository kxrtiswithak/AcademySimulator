package com.sparta.eng80.controller;

import com.sparta.eng80.model.Trainee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TraineeManagerTest {
    final int MIN = 0;
    final int MAX = 20;

    @Test
    public void generateBetween0And20Trainees() {
        TraineeManager traineeManager = new TraineeManager();
        List<List<Trainee>> listOfListsOfTrainees = new ArrayList<>();
        for (int count = 0; count < 5; count++) {
            List<Trainee> listOfTrainees = traineeManager.generateNewTrainees(MIN, MAX);
            listOfListsOfTrainees.add(listOfTrainees);
        }
        for (List<Trainee> i : listOfListsOfTrainees) {
            Assertions.assertTrue(i.size() >= 0 && i.size() <= 20);
        }
    }

    @Test
    public void generateUniqueTraineeLists() {
        TraineeManager traineeManager = new TraineeManager();
        List<Trainee> traineeList1 = traineeManager.generateNewTrainees(MIN, MAX);
        List<Trainee> traineeList2 = traineeManager.generateNewTrainees(MIN, MAX);
        Assertions.assertFalse(traineeList1.equals(traineeList2));
    }
}
