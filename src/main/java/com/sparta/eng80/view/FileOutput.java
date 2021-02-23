package com.sparta.eng80.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

public class FileOutput {
    private final String fileURL = "/resources/simulationOutput.txt";
    private Logger logger = LogManager.getLogger(FileOutput.class);

    public void outputToFile() {
        if (fileExists()) {
            try {
                FileWriter writer = new FileWriter(fileURL);
                writer.write("\nOpen Training Centres: " + formatResults(""));
                writer.write("\nFull Training Centres: " + formatResults(""));
                writer.write("\nTotal number of Trainees in Training Centres: " + formatResults(""));
                writer.write("\nTotal number of Trainees in waiting list: " + formatResults(""));
                writer.close();
            } catch (IOException e) {
                logger.error("Error writing to file.");
                e.printStackTrace();
            }
        } else {
            logger.error("File does not exist.");
            try {
                File file = new File(fileURL);
                file.createNewFile();
            } catch (IOException e) {
                logger.error("Error creating file.");
            }
        }
    }

    private boolean fileExists() {
        File file = new File(fileURL);
        return file.isFile();
    }

    private String formatResults(String unformattedText) {
        double value = Double.parseDouble(unformattedText);
        return NumberFormat.getInstance().format(value);
    }
}
