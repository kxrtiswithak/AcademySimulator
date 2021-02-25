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

    private String overallProjectTime;
    private int openTrainingHubs;
    private int fullTrainingHubs;
    private int closedTrainingHubs;
    private int openTechHubs;
    private int fullTechHubs;
    private int closedTechHubs;
    private int openBootcamps;
    private int fullBootcamps;
    private int closedBootcamps;
    private int javaInTraining;
    private int javaInWaiting;
    private int cSharpInTraining;
    private int cSharpInWaiting;
    private int dataInTraining;
    private int dataInWaiting;
    private int devOpsInTraining;
    private int devOpsInWaiting;
    private int businessInTraining;
    private int businessInWaiting;

    public FileOutput(String overallProjectTime, int openTrainingHubs, int fullTrainingHubs, int closedTrainingHubs, int openTechHubs, int fullTechHubs, int closedTechHubs, int openBootcamps, int fullBootcamps, int closedBootcamps, int javaInTraining, int javaInWaiting, int cSharpInTraining, int cSharpInWaiting, int dataInTraining, int dataInWaiting, int devOpsInTraining, int devOpsInWaiting, int businessInTraining, int businessInWaiting)
    {
        this.overallProjectTime = overallProjectTime;
        this.openTrainingHubs = openTrainingHubs;
        this.fullTrainingHubs = fullTrainingHubs;
        this.closedTrainingHubs = closedTrainingHubs;
        this.openTechHubs = openTechHubs;
        this.fullTechHubs = fullTechHubs;
        this.closedTechHubs = closedTechHubs;
        this.openBootcamps = openBootcamps;
        this.fullBootcamps = fullBootcamps;
        this.closedBootcamps = closedBootcamps;
        this.javaInTraining = javaInTraining;
        this.javaInWaiting = javaInWaiting;
        this.cSharpInTraining = cSharpInTraining;
        this.cSharpInWaiting = cSharpInWaiting;
        this.dataInTraining = dataInTraining;
        this.dataInWaiting = dataInWaiting;
        this.devOpsInTraining = devOpsInTraining;
        this.devOpsInWaiting = devOpsInWaiting;
        this.businessInTraining = businessInTraining;
        this.businessInWaiting = businessInWaiting;
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
        return overallProjectTime +
            "\nTraining Centres:" +
                "\n\tTraining Hubs:" +
                    "\n\t\tOpen: " + formatResults(openTrainingHubs) +
                    "\n\t\tFull: " + formatResults(fullTrainingHubs) +
                    "\n\t\tClosed: " + formatResults(closedTrainingHubs) +
                "\n\tTech Hubs:" +
                    "\n\t\tOpen: " + formatResults(openTechHubs) +
                    "\n\t\tFull: " + formatResults(fullTechHubs) +
                    "\n\t\tClosed: " + formatResults(closedTechHubs) +
                "\n\tBootcamps:" +
                    "\n\t\tOpen: " + formatResults(openBootcamps) +
                    "\n\t\tFull: " + formatResults(fullBootcamps) +
                    "\n\t\tClosed: " + formatResults(closedTechHubs) +
            "\nTrainees:" +
                "\n\tJava:" +
                    "\n\t\tIn Training: " + formatResults(javaInTraining) +
                    "\n\t\tWaiting List: " + formatResults(javaInWaiting) +
                "\n\tC#:" +
                    "\n\t\tIn Training: " + formatResults(cSharpInTraining) +
                    "\n\t\tWaiting List: " + formatResults(cSharpInWaiting) +
                "\n\tData:" +
                    "\n\t\tIn Training: " + formatResults(dataInTraining) +
                    "\n\t\tWaiting List: " + formatResults(dataInWaiting) +
                "\n\tDevOps:" +
                    "\n\t\tIn Training: " + formatResults(devOpsInTraining) +
                    "\n\t\tWaiting List: " + formatResults(devOpsInWaiting) +
                "\n\tBusiness" +
                    "\n\t\tIn Training: " + formatResults(businessInTraining) +
                    "\n\t\tWaiting List: " + formatResults(businessInWaiting);

//        return  overallProjectTime +
//                "\nOpen Training Centres: " + formatResults(openTrainingCentres) +
//                "\nFull Training Centres: " + formatResults(fullTrainingCentres) +
//                "\nTotal number of Trainees in Training Centres: " + formatResults(traineesInTraining) +
//                "\nTotal number of Trainees in waiting list: " + formatResults(traineesInWaitingList);
    }

    private boolean fileExists() {
        File file = new File(fileURL);
        return file.isFile();
    }

    private String formatResults(int unformattedNumber) {
        return NumberFormat.getInstance().format((double) unformattedNumber);
    }
}
