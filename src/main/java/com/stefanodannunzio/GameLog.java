package com.stefanodannunzio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameLog {
    static final String LOG_FILE_NAME = "game_logs.txt";
    static final String DATA_FILE_NAME = "data.txt";
    private static int logCount; // Global level logs counter

    public static void startLogging() {
        readLogCountFromDataFile();
        logCount++; 
        writeLogHeader();
    }

    public static void writeToBothConsoleAndFile(String message) {
        
        System.out.println(message);

        
        writeToFile(message);

        
        updateDataFile();
    }

    private static void writeToFile(String message) {
        try {
            File logFile = new File(LOG_FILE_NAME);
            File dataFile = new File(DATA_FILE_NAME);

            // Data file creation if not exists
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                writeToDataFile("0");
            }

            // Write message to log file
            writeToLogFile(message);

            // Update the number of logs in the data file
            updateDataFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToLogFile(String message) {
        try {
            FileWriter writer = new FileWriter(LOG_FILE_NAME, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToDataFile(String message) {
        try {
            FileWriter writer = new FileWriter(DATA_FILE_NAME, false);
            writer.write(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateDataFile() {
        writeToDataFile(String.valueOf(logCount));
    }

    private static void writeLogHeader() {
        writeToLogFile("///////////////////////////////////////////////////////////");
        writeToLogFile("Log No. " + logCount);
        writeToLogFile("///////////////////////////////////////////////////////////");
    }

    private static void readLogCountFromDataFile() {
        try {
            File dataFile = new File(DATA_FILE_NAME);
            if (dataFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                String logCountStr = reader.readLine();
                logCount = Integer.parseInt(logCountStr);
            } else {
                logCount = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}