package com.sparta.eng80.controller;

import com.sparta.eng80.util.Date;

public interface Output {

    String overallProjectTime(Date endingDate); //Conversion from months to years

    int outputNumOfTrainees();

    int outputNumOfTraineesInTraining();

    int outputNumOfTraineesInWaitingList();

    int outputNumOfCentres();

    int outputNumOfOpenCentres();

    int outputNumOfFullCentres();


}
