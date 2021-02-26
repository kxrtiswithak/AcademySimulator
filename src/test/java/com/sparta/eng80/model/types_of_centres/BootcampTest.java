package com.sparta.eng80.model.types_of_centres;

import com.sparta.eng80.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BootcampTest {

    @Test
    public void createBootcampTest() {
        Bootcamp bootcamp = new Bootcamp("", Date.now());
        Assertions.assertNotNull(bootcamp);
    }

}