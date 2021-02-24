package com.sparta.eng80.controller;

import com.sparta.eng80.model.Trainee;
import com.sparta.eng80.util.Printer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TraineeManagerTest {
    private final int MIN = 20;
    private final int MAX = 30;
    private TraineeManager traineeManager;

    @BeforeEach
    public void setup() {
        traineeManager = new TraineeManager();
    }

    @Test
    public void generateBetween20And30Trainees() {
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
        List<Trainee> traineeList1 = traineeManager.generateNewTrainees(MIN, MAX);
        List<Trainee> traineeList2 = traineeManager.generateNewTrainees(MIN, MAX);
        Printer.printString(""+ traineeList1.size() + " " +traineeList2.size());
        Assertions.assertFalse(traineeList1.equals(traineeList2));
    }

    @Test
    public void addTraineeToWaitingListTest() {
        List<Trainee> traineeList1 = traineeManager.generateNewTrainees(MIN, MAX);
        Printer.printString(""+traineeList1.size());
        traineeManager.addToWaitingList(traineeList1);
        Assertions.assertNotNull(traineeManager.getWaitingList());
    }
}
