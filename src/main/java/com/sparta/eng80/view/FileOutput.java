package com.sparta.eng80.view;

import com.sparta.eng80.model.CourseType;
import com.sparta.eng80.model.TrainingCentre;
import com.sparta.eng80.model.types_of_centres.Bootcamp;
import com.sparta.eng80.model.types_of_centres.TechCentre;
import com.sparta.eng80.model.types_of_centres.TrainingHub;
import com.sparta.eng80.util.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Map;

public class FileOutput {
    private final String fileURL = "resources/simulationOutput.txt";
    private Logger logger = LogManager.getLogger(FileOutput.class);

    private String overallProjectTime;

    private Map<Class<? extends TrainingCentre>, int[]> trainingCentres;
    private Map<CourseType, int[]> trainees;

    public FileOutput(String overallProjectTime, Map<Class<? extends TrainingCentre>, int[]> trainingCentres, Map<CourseType, int[]> trainees) {
        this.overallProjectTime = overallProjectTime;
        this.trainingCentres = trainingCentres;
        this.trainees = trainees;
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
        int[] trainingHubs = trainingCentres.get(TrainingHub.class);
        int[] techCentres = trainingCentres.get(TechCentre.class);
        int[] bootCamps = trainingCentres.get(Bootcamp.class);

        int[] java = trainees.get(CourseType.JAVA);
        int[] cSharp = trainees.get(CourseType.C_SHARP);
        int[] data = trainees.get(CourseType.DATA);
        int[] devOps = trainees.get(CourseType.DEVOPS);
        int[] business = trainees.get(CourseType.BUSINESS);

        return overallProjectTime +
            "\nTraining Centres:" +
                "\n\tTraining Hubs:" +
                    "\n\t\tOpen: " + formatResults(trainingHubs[0]) +
                    "\n\t\tFull: " + formatResults(trainingHubs[1]) +
                    "\n\t\tClosed: " + formatResults(trainingHubs[2]) +
                "\n\tTech Centre:" +
                    "\n\t\tOpen: " + formatResults(techCentres[0]) +
                    "\n\t\tFull: " + formatResults(techCentres[1]) +
                    "\n\t\tClosed: " + formatResults(techCentres[2]) +
                "\n\tBootcamps:" +
                    "\n\t\tOpen: " + formatResults(bootCamps[0]) +
                    "\n\t\tFull: " + formatResults(bootCamps[1]) +
                    "\n\t\tClosed: " + formatResults(bootCamps[2]) +
            "\nTrainees:" +
                "\n\tJava:" +
                    "\n\t\tIn Training: " + formatResults(java[0]) +
                    "\n\t\tWaiting List: " + formatResults(java[1]) +
                "\n\tC#:" +
                    "\n\t\tIn Training: " + formatResults(cSharp[0]) +
                    "\n\t\tWaiting List: " + formatResults(cSharp[1]) +
                "\n\tData:" +
                    "\n\t\tIn Training: " + formatResults(data[0]) +
                    "\n\t\tWaiting List: " + formatResults(data[1]) +
                "\n\tDevOps:" +
                    "\n\t\tIn Training: " + formatResults(devOps[0]) +
                    "\n\t\tWaiting List: " + formatResults(devOps[1]) +
                "\n\tBusiness" +
                    "\n\t\tIn Training: " + formatResults(business[0]) +
                    "\n\t\tWaiting List: " + formatResults(business[1]);

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
