/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: ControllerException.java
 * description: a basic ControllerException class to throw an exception
 * date: April 12, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * ControllerException
 *
 * Purpose and Description
 *
 * a class to throw an exception in console more defined way to understand what is wrong in the system
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
 * public class ControllerException extends Exception
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public ControllerException(String errorMsg)
 *
 * public String toString()
 *  - Show the exception in the console more detailed way.
 *
 *
 */

/**
 * CODE...
 */

/**
 * Important imported packages to run this file
 */

// class starts
public class ControllerException extends Exception {
    public ControllerException(String errorMsg){
        super(errorMsg);
    }

    public String toString() {
        return "\nControllerException " + "errorMessage = " + super.getMessage() +"\n" ;
    }
}///:~