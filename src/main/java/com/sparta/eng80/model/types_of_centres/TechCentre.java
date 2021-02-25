package com.sparta.eng80.model.types_of_centres;

import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.util.Date;

import java.time.LocalDate;

public class TechCentre extends TrainingCentre {

    public TechCentre(String name, Date openDate) {
        super(name, openDate);
        this.MAX_SIZE = 200;
    }

}
