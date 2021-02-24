package com.sparta.eng80.model;

import com.sparta.eng80.util.RandomGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum CourseType {
    JAVA, C_SHARP, DATA, DEVOPS, BUSINESS;

    public String getCourseType() {
        switch (this) {
            case JAVA:
                return "Java";
            case C_SHARP:
                return "C#";
            case DATA:
                return "Data";
            case DEVOPS:
                return "DevOps";
            case BUSINESS:
                return "Business";
            default:
                return null;
        }
    }

    private static final List<CourseType> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static RandomGenerator randomGenerator = new RandomGenerator(1234987293479834781L);

    public static CourseType randomCourseType() {
        return VALUES.get(randomGenerator.inRange(0, SIZE - 1));
    }
}
