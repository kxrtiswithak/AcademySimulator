package com.sparta.eng80.controller;

import com.sparta.eng80.model.centre.*;
import com.sparta.eng80.model.trainee.CourseType;
import com.sparta.eng80.model.trainee.Trainee;
import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.RandomGenerator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrainingCentreManager {

    private final Date startDate;
    private Date lastCentreAddedDate;
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private final ArrayList<TrainingCentre> listOfTrainingCentres = new ArrayList<>();
    private final TrainingCentreFactory trainingCentreFactory = new TrainingCentreFactory();
    private final TraineeManager traineeManager;
    private int numberOfBootcamps = 0;

    public TrainingCentreManager(Date startDate, TraineeManager traineeManager) {
        this.startDate = lastCentreAddedDate = startDate;
        this.traineeManager = traineeManager;
    }

    public void randomlyGenerateCentre(Date currentDate) {
        if (currentDate.isEqual(lastCentreAddedDate.plusMonths(BigInteger.TWO)) || currentDate.isEqual(startDate))
        {
            int lowerBound = numberOfBootcamps < 2 ? 1 : 2;
            int choice = randomGenerator.inRange(lowerBound, 3);
            switch (choice)
            {
                case 1:
                    generateNewTrainingCentre(Bootcamp.class, currentDate);
                    numberOfBootcamps++;
                    break;
                case 2:
                    generateNewTrainingCentre(TechCentre.class, currentDate);
                    break;
                case 3:
                    generateNewTrainingCentre(TrainingHub.class, currentDate);
                    generateNewTrainingCentre(TrainingHub.class, currentDate);
                    generateNewTrainingCentre(TrainingHub.class, currentDate);
                    break;
            }
            lastCentreAddedDate = currentDate;
        }
    }

    private void generateNewTrainingCentre(Class<? extends TrainingCentre> trainingCentreType, Date currentDate) {
        TrainingCentre trainingCentre = trainingCentreFactory.generateTrainingCentre(trainingCentreType, currentDate);
        listOfTrainingCentres.add(trainingCentre);
    }

    public void updateCentre() {
        //For all the training centres, update their age and trainee age.
        for (TrainingCentre trainingCentre : listOfTrainingCentres) {
            trainingCentre.increaseAge();
            for (Trainee trainee : trainingCentre.getInTraining()) {
                trainee.incrementMonthsInTraining();
                if (trainee.getMonthsInTraining() == 12) {
                    traineeManager.addToBench(trainee);
                }
            }
            checkToClose(trainingCentre);
        }
    }

    private void checkToClose(TrainingCentre trainingCentre) {
        if ((trainingCentre.getAge().equals(BigInteger.valueOf(3)) && !trainingCentre.isClosed() && trainingCentre.getClass() == Bootcamp.class) ||
                (trainingCentre.getAge().equals(BigInteger.TWO) && !trainingCentre.isClosed() && trainingCentre.getClass() != Bootcamp.class)) {
            //If so the size is checked. If below 25 the centre is closed and trainees are reallocated
            if (trainingCentre.getInTraining().size() < 25) {
                trainingCentre.setClosed();
                List<Trainee> trainees = trainingCentre.clearTrainees();
                reallocateTraineesRandomly(trainees);
            }
        }
    }

    public ArrayList<TrainingCentre> getListOfTrainingCenters() {
        return listOfTrainingCentres;
    }

    private void reallocateTraineesRandomly(List<Trainee> trainees) {
        List<TrainingCentre> availableCentres = getAvailableCentres();
        if (availableCentres.size() > 0) {
            for (Iterator<Trainee> iter = trainees.iterator(); iter.hasNext(); ) {
                int centreSelection = randomGenerator.inRange(0, availableCentres.size() - 1);
                TrainingCentre trainingCentre = availableCentres.get(centreSelection);
                Trainee trainee = iter.next();
                if (trainingCentre instanceof TechCentre) {
                    TechCentre techCentre = (TechCentre) trainingCentre;
                    CourseType courseType = techCentre.getCourseType();
                    if (courseType == trainee.getCourseType()) {
                        techCentre.addTrainee(trainee);
                    } else {
                        trainingCentre.addTrainee(trainee);
                    }
                    if (trainingCentre.getSpacesAvailable() == 0) {
                        availableCentres.remove(centreSelection);
                    }
                    iter.remove();
                }
            }
        }
        if (trainees.size() > 0)
        traineeManager.addToWaitingList(trainees);
    }

    private List<TrainingCentre> getAvailableCentres() {
        List<TrainingCentre> availableCentres = new ArrayList<>();
        for (TrainingCentre trainingCentre : listOfTrainingCentres) {
            if (trainingCentre.getSpacesAvailable() != 0 && !trainingCentre.isClosed()) {
                availableCentres.add(trainingCentre);
            }
        }
        return availableCentres;
    }
}
