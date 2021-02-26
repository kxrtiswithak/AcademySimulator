package com.sparta.eng80.controller;

import com.sparta.eng80.model.client.Client;
import com.sparta.eng80.model.client.ClientFactory;
import com.sparta.eng80.model.trainee.CourseType;
import com.sparta.eng80.model.trainee.Trainee;
import com.sparta.eng80.model.trainee.TraineeFactory;
import com.sparta.eng80.util.Date;
import com.sparta.eng80.util.Period;
import com.sparta.eng80.util.RandomGenerator;

import java.util.ArrayList;

import java.util.Hashtable;
import java.util.List;

public class ClientManager {
    public List<Client> newClients = new ArrayList();
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private final ClientFactory clientFactory = new ClientFactory();

    public List<Client> randomlyGenerateClients(Date currentDate) {
        int randomVal = randomGenerator.inRange(1, 3);
        for (int i = 0; i < randomVal; i++) {
            Client client = ClientFactory.generateClient(currentDate,CourseType.randomCourseType());
            newClients.add(client);
        }
        return newClients;
    }

    public void reviewClients(Date currentDate){
        for (Client client : newClients){
            if (Period.between(client.getJoinDate(), currentDate).getYears().intValue()%1 == 0){
                client.checkSatisfaction();
            }
        }
    }
}
