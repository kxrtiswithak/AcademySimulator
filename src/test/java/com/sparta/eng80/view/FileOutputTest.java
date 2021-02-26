package com.sparta.eng80.view;

import com.sparta.eng80.controller.OutputManager;
import com.sparta.eng80.controller.TraineeManager;
import com.sparta.eng80.controller.TrainingCentreManager;
import com.sparta.eng80.model.Trainee;
import com.sparta.eng80.util.Date;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class FileOutputTest {

    @Test
    public void consoleOutputTest() {
        TraineeManager traineeManager = new TraineeManager();
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(Date.now(), traineeManager);
        List<Trainee> newTrainees = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(newTrainees);
        Date simulateUntil = Date.now();
        simulateUntil = simulateUntil.plusMonths(BigInteger.valueOf(3));
        OutputManager outputManager = new OutputManager(trainingCentreManager, traineeManager, simulateUntil, false);
        outputManager.run();
        FileOutput fileOutput = new FileOutput(
                outputManager.overallProjectTime(simulateUntil),
                outputManager.outputTrainingCentres(),
                outputManager.outputTrainees()
        );
    }

    @Test
    public void fileOutputTest() {
        TraineeManager traineeManager = new TraineeManager();
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(Date.now(), traineeManager);
        List<Trainee> newTrainees = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(newTrainees);
        Date simulateUntil = Date.now();
        simulateUntil = simulateUntil.plusMonths(BigInteger.valueOf(3));
        OutputManager outputManager = new OutputManager(trainingCentreManager, traineeManager, simulateUntil, true);
        outputManager.run();
        FileOutput fileOutput = new FileOutput(
                outputManager.overallProjectTime(simulateUntil),
                outputManager.outputTrainingCentres(),
                outputManager.outputTrainees()
        );
        fileOutput.outputToFile();
    }
}
