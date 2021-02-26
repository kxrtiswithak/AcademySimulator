package com.sparta.eng80.model.centre;

import com.sparta.eng80.model.trainee.Trainee;
import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.RandomGenerator;

import java.math.BigInteger;
import java.util.*;

public abstract class TrainingCentre {
    protected static final RandomGenerator randomGenerator = new RandomGenerator();
    protected final int maxsize;
    protected final List<Trainee> inTraining = new ArrayList<>();
    protected final Date openDate;
    protected boolean isClosed = false;
    protected int spacesAvailable;
    protected BigInteger age = BigInteger.ZERO;

    public TrainingCentre(Date openDate, int maxSize){
        this.maxsize = this.spacesAvailable = maxSize;
        this.openDate = openDate;
    }

    public boolean addTrainee(Trainee trainee) {
        if (trainee == null) {
            return false;
        }
        trainee.setIsWaiting(false);
        return inTraining.add(trainee);
    }

    public Queue<Trainee> acceptTrainees(Queue<Trainee> traineeQueue, int minNumber, int maxNumber) {
        int randomVal = randomGenerator.inRange(minNumber, maxNumber);
        for (int i = 0; i < randomVal; i++) {
            if (!traineeQueue.isEmpty()) {
                if (inTraining.size() < maxsize) {
                    Trainee trainee = traineeQueue.poll();
                    addTrainee(trainee);
                    spacesAvailable--;
                } else {
                    break;
                }
            }
        }
        return traineeQueue;
    }

    public void increaseAge() {
        age = age.add(BigInteger.ONE);
    }

    public List<Trainee> getInTraining() {
        return inTraining;
    }

    public BigInteger getAge() {
        return age;
    }

    public int getSpacesAvailable() {
        return spacesAvailable;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }

    public List<Trainee> clearTrainees() {
        List<Trainee> trainees = new ArrayList<>(inTraining);
        for (Iterator<Trainee> iter = inTraining.iterator(); iter.hasNext();) {
            Trainee trainee = iter.next();
            trainee.setIsWaiting(false);
            iter.remove();
        }
        return trainees;
    }
}
