package com.sparta.eng80.view;

import com.sparta.eng80.model.Simulation;
import com.sparta.eng80.util.Printer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);


    public static void startApplication() {
        Printer.printString("Welcome to Sparta Global Training Simulator.\n");
        Simulation simulation = new Simulation();
        inputType:
        while (true) {
            printInputTypePrompt();
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        int[] dayMonthYear = setFromProperties();
                        simulation.setSimulationFor(dayMonthYear[0], dayMonthYear[1], dayMonthYear[2]);
                        break inputType;
                    case 2:
                        break;
                    default:
                        invalidInputPrompt(option);
                        continue inputType;
                }
            }

            monthInput:
            while (true) {
                printStartPrompt();
                if (scanner.hasNextInt()) {
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            simulation.setSimulationFor(enterMonths());
                            break;
                        case 2:
                            simulation.setSimulationFor(enterYears(), enterMonths());
                            break;
                        case 3:
                            simulation.setSimulationFor(enterYears(), enterMonths(), enterDays());
                            break;
                        case 4:
                            int[] date = enterDate();
                            simulation.setSimulationUntil(date[0], date[1], date[2]);
                            break;
                        default:
                            invalidInputPrompt(option);
                            continue monthInput;
                    }
                    break inputType;
                }
            }
        }
        simulation.run();
    }

    private static int[] setFromProperties()
    {
        Properties properties = new Properties();
        int[] dayMonthYear = new int[3];
        try
        {
            properties.load(new FileReader("resources/simulation.properties"));
            dayMonthYear[0] = Integer.parseInt(properties.getProperty("days", "0"));
            dayMonthYear[1] = Integer.parseInt(properties.getProperty("months", "0"));
            dayMonthYear[2] = Integer.parseInt(properties.getProperty("years", "0"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return dayMonthYear;
    }

    private static int enterMonths() {
        Printer.printString("Please enter the number of MONTHS:");
        while (!scanner.hasNextInt()) {
            Printer.printString("Please enter the number of MONTHS:");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private static int enterDays() {
        Printer.printString("Please enter the number of DAYS:");
        while (!scanner.hasNextInt()) {
            Printer.printString("Please enter the number of DAYS:");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private static int enterYears() {
        Printer.printString("Please enter the number of YEARS:");
        while (!scanner.hasNextInt()) {
            Printer.printString("Please enter the number of YEARS:");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private static int[] enterDate() {
        int[] date = new int[3];
        Printer.printString("Please enter the YEAR (yyyy)");
        while (!scanner.hasNextInt()) {
            Printer.printString("Please enter the YEAR (yyyy)");
            scanner.nextLine();
        }
        date[0] = scanner.nextInt();

        Printer.printString("Please enter the MONTH (M)");
        while (!scanner.hasNextInt()) {
            Printer.printString("Please enter the MONTH (M)");
            scanner.nextLine();
        }
        date[1] = scanner.nextInt();

        Printer.printString("Please enter the DAY (D)");
        while (!scanner.hasNextInt()) {
            Printer.printString("Please enter the DAY (D)");
            scanner.nextLine();
        }
        date[2] = scanner.nextInt();

        return date;
    }

    public static boolean outputSelection() {
        scanner.nextLine();
        printOutputOptions();
        char c = Character.toLowerCase(scanner.nextLine().charAt(0));
        boolean outputToFile;
        while (true) {
            if (c == 'y' || c == 'n') {
                outputToFile = c == Character.toLowerCase('y');
                break;
            }
            printOutputOptions();
            c = scanner.nextLine().charAt(0);
        }
        return outputToFile;
    }

    private static void invalidInputPrompt(int selection) {
        Printer.printString(selection + " is not one of the options. Please try again.\n");
    }

    private static void printInputTypePrompt() {
        Printer.printString("How would you like to input the simulation parameters?");
        Printer.printString("\t\tPlease select an option");
        Printer.printString("\t\t\t1. Using the Properties File");
        Printer.printString("\t\t\t2. Using the Command Line");
    }

    private static void printStartPrompt() {
        Printer.printString("How long would you like the simulation to run for?");
        Printer.printString("\t\tPlease select an option:");
        Printer.printString("\t\t\t1. Enter in Months");
        Printer.printString("\t\t\t2. Enter in Years & Months");
        Printer.printString("\t\t\t3. Enter in Years, Months & Days");
        Printer.printString("\t\t\t4. Until date");
    }

    private static void printOutputOptions() {
        Printer.printString("Would you like to view output in a file?");
        Printer.printString("\t\t Please press a key:");
        Printer.printString("\t\t\t Y or N");
    }
}