package com.sparta.eng80.model;

public class Trainee {
    private final CourseType courseType = CourseType.randomCourseType();

    public CourseType getCourseType() {
        return courseType;
    }
}
