package com.sparta.eng80.model.client;

import com.sparta.eng80.model.trainee.CourseType;
import com.sparta.eng80.util.Date;


public class ClientFactory {
    public Client generateClient(Date currentDate, CourseType courseType){
        return new Client(currentDate,courseType);
    }
}
