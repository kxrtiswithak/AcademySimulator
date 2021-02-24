package com.sparta.eng80.model.types_of_centres;

import com.sparta.eng80.model.TrainingCentre;

import java.time.LocalDate;

public class Bootcamp extends TrainingCentre {

    public Bootcamp(String name, LocalDate openDate) {
        super(name, openDate);
        this.MAX_SIZE = 500;
    }


}
