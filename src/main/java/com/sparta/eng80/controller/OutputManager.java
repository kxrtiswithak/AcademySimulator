package com.sparta.eng80.controller;

import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.Period;
import com.sparta.eng80.util.Printer;
import com.sparta.eng80.view.FileOutput;

public class OutputManager implements Output {

    public Date currentDate = Date.now();
    TrainingCentreManager trainingCentreManager;
    TraineeManager traineeManager;
    Date endingDate;
    FileOutput fileOutput;
    boolean outputToFile;

    public OutputManager(TrainingCentreManager trainingCentreManager, TraineeManager traineeManager, Date endingDate, boolean outputToFile) {
        this.trainingCentreManager = trainingCentreManager;
        this.traineeManager = traineeManager;
        this.endingDate = endingDate;
        this.fileOutput = new FileOutput(outputNumOfOpenCentres(), outputNumOfFullCentres(), outputNumOfTraineesInTraining(), outputNumOfTraineesInWaitingList(), overallProjectTime(endingDate));
        this.outputToFile = outputToFile;
    }

    public void run() {
        if (this.outputToFile) {
            fileOutput.outputToFile();
        } else {
            fileOutput.outputToConsole();
        }
    }

    @Override
    public String overallProjectTime(Date endingDate) {
        Period period = Period.between(currentDate, endingDate);
        String dateOutput = //period.getDays() + " days, " +
                period.getYears() + " years and " +
                        period.getMonths() + " months";
        return "The overall time for this simulation is " + dateOutput;
    }

    @Override
    public int outputNumOfTrainees() {
        int numOfTrainees = 0;
        for (TrainingCentre tc : trainingCentreManager.getListOfTrainingCenters()) {
            numOfTrainees += tc.getInTraining().size();
        }

        numOfTrainees += traineeManager.getWaitingList().size();
        return numOfTrainees;
    }

    @Override
    public int outputNumOfTraineesInTraining() {
        int numOfTrainees = 0;
        for (TrainingCentre tc : trainingCentreManager.getListOfTrainingCenters()) {
            numOfTrainees += tc.getInTraining().size();
        }
        return numOfTrainees;
    }

    @Override
    public int outputNumOfTraineesInWaitingList() {
        return traineeManager.getWaitingList().size();
    }

    @Override
    public int outputNumOfCentres() {
        return trainingCentreManager.getListOfTrainingCenters().size();
    } //Done

    @Override
    public int outputNumOfOpenCentres() {
        int openCentres = 0;
        for (TrainingCentre tc : trainingCentreManager.getListOfTrainingCenters()) {
            if (tc.getInTraining().size() != 100) {
                openCentres += 1;
            }
        }
        return openCentres;
    }

    @Override
    public int outputNumOfFullCentres() {
        return outputNumOfCentres() - outputNumOfOpenCentres();
    } //Done

    public void outputProjectResults() {
        Printer.printString(overallProjectTime(endingDate));

    }

}
