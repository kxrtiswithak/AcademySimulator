package com.sparta.eng80.model.trainee;

public class Trainee {
    private final CourseType courseType;
    private boolean isWaiting = true;
    private short monthsInTraining;

    protected Trainee(CourseType courseType) {
        this.courseType = courseType;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setIsWaiting(boolean isWaiting) {
        this.isWaiting = isWaiting;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public short getMonthsInTraining() {
        return monthsInTraining;
    }

    public void incrementMonthsInTraining() {
        monthsInTraining++;
    }
}
