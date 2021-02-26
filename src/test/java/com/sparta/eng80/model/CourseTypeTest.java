package com.sparta.eng80.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTypeTest {

    @Test
    void courseTypeTest() {
        CourseType courseType = CourseType.JAVA;
        assertNotNull(courseType.getCourseType());
    }

    @Test
    void randomCourseType() {
        Assertions.assertNotNull(CourseType.randomCourseType());
    }
}