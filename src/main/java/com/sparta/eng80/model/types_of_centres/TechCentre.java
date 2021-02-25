package com.sparta.eng80.model.types_of_centres;

import com.sparta.eng80.model.CourseType;
import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.util.Date;

public class TechCentre extends TrainingCentre {

    public CourseType courseType;

    public TechCentre(String name, Date openDate) {
        super(name, openDate);
        this.MAX_SIZE = 200;
        this.courseType = CourseType.randomCourseType();
    }

    public CourseType getCourseType() {
        return courseType;
    }
}
