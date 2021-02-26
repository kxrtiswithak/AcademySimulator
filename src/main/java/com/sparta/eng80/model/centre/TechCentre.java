package com.sparta.eng80.model.centre;

import com.sparta.eng80.model.trainee.CourseType;
import com.sparta.eng80.model.trainee.Trainee;
import com.sparta.eng80.util.Date;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class TechCentre extends TrainingCentre {

    public CourseType courseType;

    public TechCentre(Date openDate) {
        super(openDate,200);
        this.courseType = CourseType.randomCourseType();
    }

    @Override
    public Queue<Trainee> acceptTrainees(Queue<Trainee> traineeQueue, int minNumber, int maxNumber) {
        int randomVal = randomGenerator.inRange(minNumber, maxNumber);
        spacesAvailable = maxNumber - randomVal;
        Stack<Trainee> queueHead = new Stack<>();
        for (int i = 0; i < randomVal; i++) {
            if (!traineeQueue.isEmpty()) {
                Trainee trainee;
                while ((trainee = traineeQueue.peek()) != null) {
                    if (trainee.getCourseType() != courseType) {
                        queueHead.add(traineeQueue.poll());
                    } else {
                        break;
                    }
                }
                if (inTraining.size() < maxsize) {
                    trainee = traineeQueue.poll();
                    addTrainee(trainee);
                } else {
                    break;
                }
            }
        }
        while (!queueHead.isEmpty()) {
            ((LinkedBlockingDeque<Trainee>) traineeQueue).addFirst(queueHead.pop());
        }
        return traineeQueue;
    }

    public CourseType getCourseType() {
        return courseType;
    }
}
