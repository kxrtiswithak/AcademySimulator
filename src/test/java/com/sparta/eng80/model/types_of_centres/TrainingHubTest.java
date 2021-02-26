package com.sparta.eng80.model.types_of_centres;

import com.sparta.eng80.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingHubTest {

    static TrainingHub trainingHub;

    @BeforeAll
    static void first() {
        trainingHub = new TrainingHub("", Date.now());
    }

    @Test
    public void createTrainingHubTest() {
        Assertions.assertNotNull(trainingHub);
    }

}