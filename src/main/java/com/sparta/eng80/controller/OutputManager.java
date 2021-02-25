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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.fileOutput = new FileOutput(overallProjectTime(endingDate), outputTrainingCentres(), outputTrainees());
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
        return "\nThe overall time for this simulation is " + dateOutput;
    }

    public Map<Class<? extends TrainingCentre>, int[]> outputTrainingCentres() {
        List<TrainingCentre> trainingCentreList = trainingCentreManager.getListOfTrainingCenters();
        Map<Class<? extends TrainingCentre>, int[]> trainingCentres = new HashMap<>();
        int[] trainingHubs = new int[3];
        int[] techCentres = new int[3];
        int[] bootcamps = new int[3];
        for (TrainingCentre trainingCentre : trainingCentreList) {
            if (trainingCentre instanceof TrainingHub) {
                updateCentreCount(trainingCentre, trainingHubs);
            } else if (trainingCentre instanceof TechCentre) {
                updateCentreCount(trainingCentre, techCentres);
            } else if (trainingCentre instanceof Bootcamp) {
               updateCentreCount(trainingCentre, bootcamps);
            }
        }
        return Map.of(TrainingHub.class, trainingHubs, TechCentre.class, techCentres,
                Bootcamp.class, bootcamps);
    }

    private void updateCentreCount(TrainingCentre centre, int[] centreCounters) {
        if (centre.spacesAvailable == 0) {
            centreCounters[1]++;
        } else if (centre.isClosed) {
            centreCounters[2]++;
        } else {
            centreCounters[0]++;
        }
    }

    public Map<CourseType, int[]> outputTrainees() {
        List<Trainee> traineeList = traineeManager.getAllTrainees();
        int[] java = new int[2];
        int[] cSharp = new int[2];
        int[] data = new int[2];
        int[] devOps = new int[2];
        int[] business = new int[2];
        for (Trainee trainee : traineeList) {
            CourseType courseType = trainee.getCourseType();
            switch (courseType) {
                case JAVA:
                    updateTraineeTypeCount(trainee, java);
                    break;
                case C_SHARP:
                    updateTraineeTypeCount(trainee, cSharp);
                    break;
                case DATA:
                    updateTraineeTypeCount(trainee, data);
                    break;
                case DEVOPS:
                    updateTraineeTypeCount(trainee, devOps);
                    break;
                case BUSINESS:
                    updateTraineeTypeCount(trainee, business);
                    break;
            }
        }
        return Map.of(CourseType.JAVA, java, CourseType.C_SHARP, cSharp,
                CourseType.DATA, data, CourseType.DEVOPS, devOps, CourseType.BUSINESS, business);
    }

    private void updateTraineeTypeCount(Trainee trainee, int[] traineeCounters) {
        if (!trainee.isWaiting()) {
            traineeCounters[0]++;
        } else {
            traineeCounters[1]++;
        }
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
    }


    public int outputNumOfOpenCentres() {
        int openCentres = 0;
        for (TrainingCentre tc : trainingCentreManager.getListOfTrainingCenters()) {
            if (tc.getInTraining().size() != tc.MAX_SIZE) {
                openCentres += 1;
            }
        }
        return openCentres;
    }


    public int outputNumOfFullCentres() {
        return outputNumOfCentres() - outputNumOfOpenCentres();
    }

    public void outputProjectResults() {
        Printer.printString(overallProjectTime(endingDate));

    }

}
