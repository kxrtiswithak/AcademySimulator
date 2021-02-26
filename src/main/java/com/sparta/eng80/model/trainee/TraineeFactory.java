package com.sparta.eng80.model.trainee;

public class TraineeFactory
{
	public Trainee generateTrainee(CourseType courseType) {
		return new Trainee(courseType);
	}
}
