/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: WritingFile.java
 * description: a basic WritingFile class to write file in the directory
 * date: April 12, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * WritingFile
 *
 * Purpose and Description
 *
 * a class to write file "error.log", "fix.log" to store the info.
 *
 * This program does not use User input, all inputs are from directory based.
 *
 * Sun Java SDK version 1.3 or better.
 *
 * Compiling and running instructions
 * Assuming SDK 1.3 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 *
 * Compile:    See GreenhouseControls class file
 * Run:        See GreenhouseControls class file
 *
 */

/**
 * Classes
 *
 * public class WritingFile
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public WritingFile(int errorCode, boolean fixed) throws IOException
 *  - errorCode decides what to write in the file
 *  - fixed decides to write in "error.log" - (false) or "fix.log" - (true)
 *
 * getProblemName(int errorCode)
 * getTime()
 *
 *
 * Instance Variables
 *
 * None
 *
 */

/**
 * CODE...
 */

/**
 * Important imported packages to run this file
 */

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * class starts
 */
public class WritingFile {

    public WritingFile(int errorCode, boolean fixed) throws IOException {
        FileWriter fileWriterErrorLog;
        // false means it is not fixed, so write in error.log file
        if (!fixed) {
            // it will keep track of the errors in the file.
            fileWriterErrorLog = new FileWriter("error.log", true);
            PrintWriter printWriter = new PrintWriter(fileWriterErrorLog, true);

            // writing contents
            String logEntry = "Time: " + getTime() + "\n" + "Error Message: ";
            logEntry = logEntry + getProblemName(errorCode) + "\t" + "Error Code: " + errorCode;

            printWriter.println(logEntry);
            printWriter.println();

            System.out.println();
            System.out.println(logEntry);

            printWriter.close();
        } // if (!fixed)

        // true means it is fixed, so write in fix.log file
        else {
            // it will keep track of the fixes in the file.
            fileWriterErrorLog = new FileWriter("fix.log", true);
            PrintWriter printWriter = new PrintWriter(fileWriterErrorLog, true);

            // writing contents
            String logEntry = "Time: " + getTime() + "\n" + "Fixed Message: ";
            logEntry = logEntry + getProblemName(errorCode) + "\t" + "No Error Code ";

            printWriter.println(logEntry);
            printWriter.println();

            System.out.println();
            System.out.println(logEntry);
            System.out.println();

            printWriter.close();
        } // else
        /**
         * Closing the fileWriter after writing the file.
         */
        fileWriterErrorLog.close();
    }// end of public WritingFile(int errorCode, boolean fixed) throws IOException

    /**
     * Get the problem name to write on file.
     * @param errorCode - problem names depends on errorCode
     *                  1 - Window Malfunction
     *                  2 - Power Out
     * @return
     */
    private String getProblemName(int errorCode){
        if (errorCode == 1) return "Window Malfunction ";
        else if (errorCode == 2) return "Power Out ";
        return "unknown error";
    }

    /**
     * Get the current System time in a format
     * @return - String value - System time
     * Note: idea taken from
     * https://www.geeksforgeeks.org/localdatetime-format-method-in-java/
     */
    private String getTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = formatter.format(localDateTime);
        return formatDateTime;
    }

}///:~
