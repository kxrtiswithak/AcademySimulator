package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;

import java.util.ArrayList;
import java.util.List;

public class TrainingCenter {
    //private static int ID;
    private String name;
    private List<Trainee> inTraining;

    public TrainingCenter(String name) {
        this.name = name;
        this.inTraining = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return getInTraining().size();
    }

    public List<Trainee> getInTraining() {
        return inTraining;
    }

    public boolean addToTrainingCentre(Trainee trainee) {
        if (trainee == null) {
            Printer.printString("Null trainee cannot be added, sorry");
            return false;
        } else if (inTraining.contains(trainee)) {
            Printer.printString("Trainee already exists, so wont be added");
            return false;
        } else if (getSize() == 100) {
            Printer.printString("Training Centre is full, please add to a different one.");
            return false;
        }
        inTraining.add(trainee);
        return true;
    }
}
