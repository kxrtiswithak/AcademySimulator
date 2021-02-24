package com.sparta.eng80;

import com.sparta.eng80.model.Simulation;
import com.sparta.eng80.util.Printer;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);


    public static void startApplication() {
        Printer.printString("Welcome to Sparta Global Training Simulator.\n");
        printStartPrompt();
        while (!scanner.hasNextInt()) {
            printStartPrompt();
            scanner.nextLine();
        }
        int option = scanner.nextInt();

        Simulation simulation = new Simulation();
        switch (option) {
            case 1: simulation.setSimulationFor(enterMonths());
                break;
            case 2: simulation.setSimulationFor(enterYears(), enterMonths());
                break;
            case 3: simulation.setSimulationFor(enterYears(), enterMonths(), enterDays());
                break;
            case 4:
                int[] date = enterDate();
                simulation.setSimulationUntil(date[0], date[1], date[2]);
                break;
        }
        simulation.run();
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

        Printer.printString("Please enter the YEAR (D)");
        while (!scanner.hasNextInt()) {
            Printer.printString("Please enter the YEAR (D)");
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
