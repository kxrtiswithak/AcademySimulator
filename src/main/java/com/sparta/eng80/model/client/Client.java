package com.sparta.eng80.model.client;

import com.sparta.eng80.model.trainee.CourseType;
import com.sparta.eng80.model.trainee.Trainee;
import com.sparta.eng80.util.RandomGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client {
    private final CourseType courseType;
    private final int traineesNeeded;
    private boolean isHappy;
    private Date joinDate;
    private RandomGenerator randomGenerator;
    private List<Trainee> clientTrainees = new ArrayList<>();

    protected Client(Date joinDate, CourseType courseType) {
        this.joinDate = joinDate;
        this.courseType = courseType;
        //TODO MAX trainees to be decided
        this.traineesNeeded = randomGenerator.inRange(15, 30);
    }

    public int getTraineesNeeded() {
        return traineesNeeded;
    }

    public boolean getIsHappy(){
        return isHappy;
    }

    public void checkSatisfaction(){
        if (clientTrainees.size() == traineesNeeded){
            isHappy = true;
        }
    }

    public List<Trainee> getTrainees() {
        return clientTrainees;
    }

    public CourseType getCourseType() {
        return courseType;
    }
}
