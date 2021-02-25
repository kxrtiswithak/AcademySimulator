package com.sparta.eng80.controller;

import com.sparta.eng80.model.CourseType;
import com.sparta.eng80.model.Trainee;
import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.model.types_of_centres.Bootcamp;
import com.sparta.eng80.model.types_of_centres.TechCentre;
import com.sparta.eng80.model.types_of_centres.TrainingHub;
import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.Period;
import com.sparta.eng80.util.Printer;
import com.sparta.eng80.view.FileOutput;

import java.util.List;

public class OutputManager {

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
        int[] trainingCentres = outputTrainingCentres();
        int[] trainees = outputTrainees();
        this.fileOutput = new FileOutput(overallProjectTime(endingDate), trainingCentres[0], trainingCentres[2], trainingCentres[1],
                trainingCentres[3], trainingCentres[5], trainingCentres[4], trainingCentres[6], trainingCentres[8], trainingCentres[7],
                trainees[0], trainees[1], trainees[2], trainees[3], trainees[4], trainees[5], trainees[6], trainees[7], trainees[8], trainees[9]);
        this.outputToFile = outputToFile;
    }

    public void run() {
        if (this.outputToFile) {
            fileOutput.outputToFile();
        } else {
            fileOutput.outputToConsole();
        }
    }


    public String overallProjectTime(Date endingDate) {
        Period period = Period.between(currentDate, endingDate);
        String dateOutput = //period.getDays() + " days, " +
                period.getYears() + " years and " +
                        period.getMonths() + " months";
        return "The overall time for this simulation is " + dateOutput;
    }

    public int[] outputTrainingCentres() {
        List<TrainingCentre> trainingCentreList = trainingCentreManager.getListOfTrainingCenters();
        int[] trainingCentres = new int[9];
        for (TrainingCentre trainingCentre : trainingCentreList) {
            if (trainingCentre instanceof TrainingHub) {
                TrainingHub trainingHub = (TrainingHub) trainingCentre;
                if (trainingHub.spacesAvailable == 0) {
                    trainingCentres[0] ++;
                } else if (trainingHub.isClosed) {
                    trainingCentres[2] ++;
                } else {
                    trainingCentres[1] ++;
                }
            } else if (trainingCentre instanceof TechCentre) {
                TechCentre techCentre = (TechCentre) trainingCentre;
                if (techCentre.spacesAvailable == 0) {
                    trainingCentres[3] ++;
                } else if (techCentre.isClosed) {
                    trainingCentres[5] ++;
                } else {
                    trainingCentres[4] ++;
                }
            } else if (trainingCentre instanceof Bootcamp) {
                Bootcamp bootcamp = (Bootcamp) trainingCentre;
                if (bootcamp.spacesAvailable == 0) {
                    trainingCentres[6] ++;
                } else if (bootcamp.isClosed) {
                    trainingCentres[8] ++;
                } else {
                    trainingCentres[7] ++;
                }
            }
        }
        return trainingCentres;
    }

    public int[] outputTrainees() {
        List<Trainee> traineeList = traineeManager.getAllTrainees();
        int[] trainees = new int[10];
        for (Trainee trainee : traineeList) {
            CourseType courseType = trainee.getCourseType();
            switch (courseType) {
                case JAVA:
                    if (!trainee.isWaiting()) {
                        trainees[0] ++;
                    } else {
                        trainees[1] ++;
                    }
                    break;
                case C_SHARP:
                    if (!trainee.isWaiting()) {
                        trainees[2] ++;
                    } else {
                        trainees[3] ++;
                    }
                    break;
                case DATA:
                    if (!trainee.isWaiting()) {
                        trainees[4] ++;
                    } else {
                        trainees[5] ++;
                    }
                    break;
                case DEVOPS:
                    if (!trainee.isWaiting()) {
                        trainees[6] ++;
                    } else {
                        trainees[7] ++;
                    }
                    break;
                case BUSINESS:
                    if (!trainee.isWaiting()) {
                        trainees[8] ++;
                    } else {
                        trainees[9] ++;
                    }
                    break;
            }
        }
        return trainees;
    }


    public int outputNumOfTrainees() {
        int numOfTrainees = 0;
        for (TrainingCentre tc : trainingCentreManager.getListOfTrainingCenters()) {
            numOfTrainees += tc.getInTraining().size();
        }

        numOfTrainees += traineeManager.getWaitingList().size();
        return numOfTrainees;
    }


    public int outputNumOfTraineesInTraining() {
        int numOfTrainees = 0;
        for (TrainingCentre tc : trainingCentreManager.getListOfTrainingCenters()) {
            numOfTrainees += tc.getInTraining().size();
        }
        return numOfTrainees;
    }


    public int outputNumOfTraineesInWaitingList() {
        return traineeManager.getWaitingList().size();
    }


    public int outputNumOfCentres() {
        return trainingCentreManager.getListOfTrainingCenters().size();
    } //Done


    public int outputNumOfOpenCentres() {
        int openCentres = 0;
        for (TrainingCentre tc : trainingCentreManager.getListOfTrainingCenters()) {
            if (tc.getInTraining().size() != 100) {
                openCentres += 1;
            }
        }
        return openCentres;
    }


    public int outputNumOfFullCentres() {
        return outputNumOfCentres() - outputNumOfOpenCentres();
    } //Done

    public void outputProjectResults() {
        Printer.printString(overallProjectTime(endingDate));

    }

}
