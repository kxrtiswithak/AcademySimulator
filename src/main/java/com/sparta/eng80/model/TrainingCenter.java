package com.sparta.eng80.model;

import java.util.List;

public class TrainingCenter {
    //private static int ID;
    private String name;
    private int size;
    private List<Trainee> inTraining;

    public TrainingCenter(String name) {
        this.name = name;
        this.size = 0;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean addTrainee(Trainee trainee) {
        return inTraining.add(trainee);
    }

    public List<Trainee> getInTraining() {
        return inTraining;
    }
}
