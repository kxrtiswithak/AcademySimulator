package com.sparta.eng80.controller;

import java.time.LocalDate;

public interface Output {

    String overallProjectTime(LocalDate endingDate); //Conversion from months to years

    int outputNumOfTrainees();

    int outputNumOfTraineesInTraining();

    int outputNumOfTraineesInWaitingList();

    int outputNumOfCentres();

    int outputNumOfOpenCentres();

    int outputNumOfFullCentres();


}
