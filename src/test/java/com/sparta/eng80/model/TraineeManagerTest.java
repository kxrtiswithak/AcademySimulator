package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TraineeManagerTest {
    final int MIN = 20;
    final int MAX = 30;

    @Test
    public void generateBetween20And30Trainees() {
        TraineeManager traineeManager = new TraineeManager();
        List<List<Trainee>> listOfListsOfTrainees = new ArrayList<>();
        for (int count = 0; count < 5; count++) {
            List<Trainee> listOfTrainees = traineeManager.generateNewTrainees(MIN, MAX);
            listOfListsOfTrainees.add(listOfTrainees);
        }
        for (List<Trainee> i : listOfListsOfTrainees) {
            Assertions.assertTrue(i.size() >= 20 && i.size() <= 30);
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
