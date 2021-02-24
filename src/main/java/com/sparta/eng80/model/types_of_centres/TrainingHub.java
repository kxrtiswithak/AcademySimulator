package com.sparta.eng80.model.types_of_centres;

import com.sparta.eng80.model.TrainingCentre;

import java.time.LocalDate;

public class TrainingHub extends TrainingCentre {
    public TrainingHub(String name, LocalDate openDate) {
        super(name, openDate);
    }
}
