package com.sparta.eng80.controller;

import com.sparta.eng80.model.trainee.CourseType;
import com.sparta.eng80.model.trainee.Trainee;
import com.sparta.eng80.model.centre.TrainingCentre;
import com.sparta.eng80.model.centre.Bootcamp;
import com.sparta.eng80.model.centre.TechCentre;
import com.sparta.eng80.model.centre.TrainingHub;
import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.Period;
import com.sparta.eng80.view.FileOutput;

import java.util.List;
import java.util.Map;

public class OutputManager {

    private final Date currentDate = Date.now();
    private final TrainingCentreManager trainingCentreManager;
    private final TraineeManager traineeManager;
    private final FileOutput fileOutput;
    private final boolean outputToFile;

    public OutputManager(TrainingCentreManager trainingCentreManager, TraineeManager traineeManager, Date endingDate, boolean outputToFile) {
        this.trainingCentreManager = trainingCentreManager;
        this.traineeManager = traineeManager;
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

    private String overallProjectTime(Date endingDate) {
        Period period = Period.between(currentDate, endingDate);
        String dateOutput = period.getYears() + " years and " +
                        period.getMonths() + " months";
        return "\nThe overall time for this simulation is " + dateOutput;
    }

    private Map<Class<? extends TrainingCentre>, int[]> outputTrainingCentres() {
        List<TrainingCentre> trainingCentreList = trainingCentreManager.getListOfTrainingCenters();
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
        if (centre.getSpacesAvailable() == 0) {
            centreCounters[1]++;
        } else if (centre.isClosed()) {
            centreCounters[2]++;
        } else {
            centreCounters[0]++;
        }
    }

    private Map<CourseType, int[]> outputTrainees() {
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
}
