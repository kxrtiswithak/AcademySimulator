package com.sparta.eng80.model.types_of_centres;

import com.sparta.eng80.model.TrainingCentre;

public class TechCentre extends TrainingCentre {

    public TechCentre(String name) {
        super(name);
        this.MAX_SIZE = 200;
    }

}
