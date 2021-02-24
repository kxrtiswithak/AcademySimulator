package com.sparta.eng80.view;

import com.sparta.eng80.util.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

public class FileOutput {
    private final String fileURL = "resources/simulationOutput.txt";
    private Logger logger = LogManager.getLogger(FileOutput.class);

    private int openTrainingCentres;
    private int fullTrainingCentres;
    private int traineesInTraining;
    private int traineesInWaitingList;
    private String overallProjectTime;

    public FileOutput(int openTrainingCentres, int fullTrainingCentres, int traineesInTraining, int traineesInWaitingList, String overallProjectTime) {
        this.openTrainingCentres = openTrainingCentres;
        this.fullTrainingCentres = fullTrainingCentres;
        this.traineesInTraining = traineesInTraining;
        this.traineesInWaitingList = traineesInWaitingList;
        this.overallProjectTime = overallProjectTime;
    }

    public void outputToFile() {
        try {
            if (!fileExists()) {
                logger.error("File does not exist.");
                File file = new File(fileURL);
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(fileURL);
            writer.write(createOutput());
            writer.close();

        } catch (IOException e) {
            logger.error("Error writing to file.");
            e.printStackTrace();
        }
    }

    public void outputToConsole() {
        Printer.printString(createOutput());
    }

    public String createOutput() {
        return  overallProjectTime +
                "\nOpen Training Centres: " + formatResults(openTrainingCentres) +
                "\nFull Training Centres: " + formatResults(fullTrainingCentres) +
                "\nTotal number of Trainees in Training Centres: " + formatResults(traineesInTraining) +
                "\nTotal number of Trainees in waiting list: " + formatResults(traineesInWaitingList);
    }

    private boolean fileExists() {
        File file = new File(fileURL);
        return file.isFile();
    }

    private int formatResults(int unformattedNumber) {
        return Integer.parseInt(NumberFormat.getInstance().format((double) unformattedNumber));
    }
}
