package com.sparta.eng80.model;

import com.sparta.eng80.controller.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class TraineeManager {

	private static final long SEED = 1397819237199981723L;
	private static final RandomGenerator randomGenerator = new RandomGenerator(SEED);

	public List<Trainee> generateNewTrainees(int minNumber, int maxNumber) {
		int randomVal = randomGenerator.inRange(minNumber, maxNumber);
		List<Trainee> newTrainees = new ArrayList<>();
		for (int i = 0; i < randomVal; i++) {
			newTrainees.add(new Trainee());
		}
		return newTrainees;
	}
}
