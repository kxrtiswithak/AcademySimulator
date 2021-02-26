package com.sparta.eng80.model.client;

import com.sparta.eng80.model.trainee.CourseType;

import java.util.Date;

public class ClientFactory {
    public static Client generateClient(Date currentDate, CourseType courseType){
        return new Client(currentDate,courseType);
    }
}
