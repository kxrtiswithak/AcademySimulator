package com.sparta.eng80.model.types_of_centres;

import com.sparta.eng80.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TechCentreTest {

    static TechCentre techCentre;


    @BeforeAll
    static void first() {
        techCentre = new TechCentre("", Date.now());
    }

    @Test
    public void createTechCentreTest() {
        Assertions.assertNotNull(techCentre);
    }

    @Test
    void getCourseType() {
        Assertions.assertNotNull(techCentre.getCourseType());
    }
}