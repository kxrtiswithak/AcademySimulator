package com.sparta.eng80.model;

public class Trainee {
    private final CourseType courseType = CourseType.randomCourseType();
    private boolean isWaiting = true;

    public CourseType getCourseType() {
        return courseType;
    }

    public void setIsWaiting(boolean isWaiting) {
        this.isWaiting = isWaiting;
    }

    public boolean isWaiting() {
        return isWaiting;
    }
}
