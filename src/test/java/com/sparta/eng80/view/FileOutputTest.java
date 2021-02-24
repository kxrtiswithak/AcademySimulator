package com.sparta.eng80.view;

import com.sparta.eng80.controller.OutputManager;
import com.sparta.eng80.controller.TraineeManager;
import com.sparta.eng80.controller.TrainingCentreManager;
import com.sparta.eng80.model.Trainee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class FileOutputTest {

    @Test
    public void fileOutputTest() {
        TrainingCentreManager trainingCentreManager = new TrainingCentreManager(LocalDate.now());
        TraineeManager traineeManager = new TraineeManager();
        List<Trainee> newTrainees = traineeManager.generateNewTrainees(20, 30);
        traineeManager.addToWaitingList(newTrainees);
        LocalDate simulateUntil = LocalDate.now();
        simulateUntil = simulateUntil.plusMonths(3);
        OutputManager outputManager = new OutputManager(trainingCentreManager, traineeManager, simulateUntil, false);
        outputManager.run();
        FileOutput fileOutput = new FileOutput(
                outputManager.outputNumOfOpenCentres(),
                outputManager.outputNumOfFullCentres(),
                outputManager.outputNumOfTraineesInTraining(),
                outputManager.outputNumOfTraineesInWaitingList(),
                outputManager.overallProjectTime(simulateUntil)
        );
        fileOutput.outputToFile();
    }
}
