package com.sparta.eng80.model;

import com.sparta.eng80.view.Printer;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class OutputManager implements Output {

    public LocalDate currentDate = LocalDate.now();
    TrainingCentreManager tcm;
    LocalDate endingDate;

    public OutputManager(LocalDate endingDate){
        this.endingDate = endingDate;
    }

    @Override
    public String overallProjectTime(LocalDate endingDate) {
        Period period = Period.between(currentDate, endingDate);
        String dateOutput = //period.getDays() + " days, " +
                period.getMonths() + " months and " +
                period.getYears() + " years";
        return "The overall time for this simulation is "+ dateOutput;
    } //Done

    @Override
    public int outputNumOfTrainees() {
        int numOfTrainees = 0;
        for (TrainingCentre tc : tcm.getListOfTrainingCenters()){
            numOfTrainees += tc.getInTraining().size();
        }
        //TODO get waiting list
        //numOfTrainees += waitingList.size();
        return numOfTrainees;
    }

    @Override
    public int outputNumOfTraineesInTraining() {
        int numOfTrainees = 0;
        for (TrainingCentre tc : tcm.getListOfTrainingCenters()){
            numOfTrainees += tc.getInTraining().size();
        }
        return numOfTrainees;
    } //Done

    @Override
    public int outputNumOfTraineesInWaitingList() {
        //TODO get waiting list
        int numOfTraineesWaiting = 0;
        //numOfTraineesWaiting += waitingList.size;
        return numOfTraineesWaiting;
    }

    @Override
    public int outputNumOfCentres() {
        return tcm.getListOfTrainingCenters().size();
    } //Done

    @Override
    public int outputNumOfOpenCentres() {
        int openCentres = 0;
        for(TrainingCentre tc : tcm.getListOfTrainingCenters()){
           if(tc.getInTraining().size() != 100){
               openCentres+=1;
           }
        }
        return openCentres;
    } //Done

    @Override
    public int outputNumOfFullCentres() {
        return outputNumOfCentres() - outputNumOfOpenCentres();
    } //Done

    public void outputProjectResults(){
        Printer.printString(overallProjectTime(endingDate));

    }

}
