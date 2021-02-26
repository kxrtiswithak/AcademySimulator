package com.sparta.eng80.model.centre;

import com.sparta.eng80.util.Date;

public class TrainingCentreFactory {

	public TrainingCentre generateTrainingCentre(Class<? extends TrainingCentre>  trainingCentreType, Date currentDate) {
		if (trainingCentreType == TrainingHub.class) {
			return new TrainingHub(currentDate);
		} else if (trainingCentreType == TechCentre.class) {
			return new TechCentre(currentDate);
		} else if (trainingCentreType == Bootcamp.class) {
			return new Bootcamp(currentDate);
		} else {
			throw new IllegalArgumentException("A " + trainingCentreType.getSimpleName() + " cannot be initialised.");
		}
	}
}
