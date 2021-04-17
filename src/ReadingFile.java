/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: ReadingFile.java
 * description: a basic ReadingFile class to read file from the directory
 * date: April 12, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * ReadingFile
 *
 * Purpose and Description
 *
 * a class to read file "examples(1-4).txt" to store the data in a container.
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
 * public class ReadingFile
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public ReadingFile(String fileName) throws FileNotFoundException
 *
 * getEventArrayList()
 *  - to get the event full of container.
 *
 * getTimeArrayList()
 *  - to get the events' time for the events
 *
 * public int getRingsOfBell()
 *  - to get how many rings to play for bell - (Bing!)
 *
 *
 * Instance Variables
 *
 * private final ArrayList<String> eventArrayList;
 *  - store the event list from the file
 * private final ArrayList<Long> timeArrayList;
 *  - store the time length
 * private int ringsOfBell;
 *  - store the "rings" only for bell.
 *
 * Technique (String Tokenizer)
 *  - First tokenize the string with delimiter (,) value into an array
 *  - Then tokenize every token with delimiter  (=) for getting the name and value
 *  - First part is for Event's Name
 *  - Second part is for Time length for that Event
 *  - another token left, then use it for Bell class rings.
 *  - Use Scanner and an appropriate regular expression.
 *
 */

/**
 * CODE...
 */

/**
 * Important imported packages to run this file
 */

import java.io.*;
import java.util.*;


/**
 * class starts
 */
public class ReadingFile {
    private final ArrayList<String> eventArrayList;
    private final ArrayList<Long> timeArrayList;
    private int ringsOfBell;

    /**
     * Constructor
     * @param fileName - string value for file name
     * @throws FileNotFoundException - if file not found
     */
    public ReadingFile(String fileName) throws FileNotFoundException {
        /**
         * initializing the variables
         */
        /**
         * private
         */
        File file = new File(fileName);
        eventArrayList = new ArrayList<>();
        timeArrayList = new ArrayList<>();

        /**
         * setting scanner to read through the file
         */
        Scanner scanner = new Scanner(file);


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            /**
             * Technique described above
             * String Tokenizer is used to read and separate different into different categories
             */

            StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
            String[] tokens = stringTokenizer.nextToken().split("=");

            eventArrayList.add(tokens[1]);

            String[] time = stringTokenizer.nextToken().split("=");
            long timeOfTheEvent = Long.parseLong(time[1]);
            timeArrayList.add(timeOfTheEvent);

            /**
             * Only for Bell. if there is any 3 parameter left to catch for "rings"
             */
            if (stringTokenizer.countTokens() == 1){
                String[] rings = stringTokenizer.nextToken().split("=");
                ringsOfBell = Integer.parseInt(rings[1]);
                //System.out.println(ringsOfBell);
            }
        }
        /**
         * Closing Scanner
         */
        scanner.close();
    } // end of public ReadingFile(String fileName) throws FileNotFoundException

    /**
     * Stored the Events from the file
     * @return - String type of ArrayList container full of Events
     */
    public ArrayList<String> getEventArrayList(){
        return eventArrayList;
    }

    /**
     * Stored the time lengths of Events from the file
     * @return - Long type of ArrayList container full of time lengths
     */
    public ArrayList<Long> getTimeArrayList(){
        return timeArrayList;
    }

    /**
     * Stored the rings for Bell class
     * @return - int value - number of rings.
     */
    public int getRingsOfBell() {
        return ringsOfBell;
    }


}///:~
