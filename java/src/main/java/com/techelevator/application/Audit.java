package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {

    private File logFile;

    public Audit(String logFileName) {
        this.logFile = new File(logFileName);
    }

    public void write(String message) {
        LocalDateTime timeStamp = LocalDateTime.now();
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String lineOfLog = timeStamp.format(targetFormat) + " " + message + "\n";

        PrintWriter writer = null;

        try {
            // Instantiate the writer object with append functionality.
            if (logFile.exists()) {
                writer = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true));
            }

            // Instantiate the writer object without append functionality.
            else {
                writer = new PrintWriter(logFile.getAbsoluteFile());
            }
        } catch (FileNotFoundException e) {

        }

        writer.append(lineOfLog);
        writer.flush();
        writer.close();

    }
}
