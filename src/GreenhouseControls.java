/***********************************************************************
 * Adapated for COMP308 Java for Programmer, 
 *		SCIS, Athabasca University
 *
 * Assignment: TME3
 *
 * @date  : Oct 21, 2005
 *
 */
/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01 - Greenhouse Controls by abstract class, inheritance, File I/O
 * Course: COMP 308
 * Athabasca University
 *
 * NOTE: Most of the programing is self-documenting or self-describing.
 * Note: The base file was already coded by creator, I am just adding some features only for assignment.
 */

/**
 * title: GreenhouseControls.java
 * description: A basic GreenhouseControls class controls I/O, inheritance.
 * date: April 12, 2020
 * @author: Steve Leung (main Author)
 * Note: Alvee Hassan Akash added functionality to complete assignment (only this edition)
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 *
 * // innerclasses/GreenhouseControls.java
 * // This produces a specific application of the
 * // control system, all in a single class. Inner
 * // classes allow you to encapsulate different
 * // functionality for each type of event.
 * // From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
 * // www.BruceEckel.com. See copyright notice in CopyRight.txt.
 *
 * I declare that this assignment is my own work which will be differently formatted in the folder
 * and that all material previously written or published in any source
 * by any other person has been duly acknowledged in the assignment. I have not submitted this work, or a significant
 * part thereof, previously as part of any academic program. In submitting this assignment I give permission to copy
 * it for assessment purposes only.
 */

/**
 * DOCUMENTATION...
 */

/**
 * GreenhouseControls
 *
 * Purpose and Description
 *
 * a class to run the complete program. It has many functionality - inner classes, interface, etc
 * Inner classes to run that helps to complete the Events and GreenhouseControls with and without
 * errors according to the assignment requirements.
 *
 * This program does use User input, all inputs are from directory based shown in the RUN-TIME.
 *
 * Sun Java SDK version 1.3 or better.
 *
 * Compiling and running instructions
 * Assuming SDK 1.3 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 *
 *
 * Compile:    javac tme3/*.java
 *             javac *.java
 *
 * Run:        java GreenhouseControls -f examples1.txt
 *
 *             java GreenhouseControls -f examples2.txt
 *
 *             java GreenhouseControls -f examples3.txt
 *             java GreenhouseControls -d dump.out
 *
 *             java GreenhouseControls -f examples4.txt
 *             java GreenhouseControls -d dump.out
 *
 */

/**
 * Classes
 *
 * public class GreenhouseControls extends Controller implements Serializable
 *
 * Interface
 *
 * interface Fixable
 *  - help to fix the errors in the GreenhouseControls Events such as Power out and Window Malfunction
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * default constructor
 *
 * public static void serializeObject(Object object)
 *  - Serialize the passed object into its current state
 *
 * public void shutdown(String problem)
 *  -Emergency shutdown the system
 *
 * public Fixable getFixable (int errorcode)
 *  - get the fixable class based on the error code that serialized before
 *
 * public int getError()
 *  - get the errorCode from the GreenhouseControls object
 *
 * public void callEventClass(String className, long time, int rings)
 *  - adding events to the List from the given parameters
 *
 *
 * Instance Variables
 *
 * private boolean light = false;
 * private boolean water = false;
 * private String thermostat = "Day";
 * private String eventsFile = "examples1.txt";
 *
 * private boolean fans;
 * private boolean windowok;
 * private boolean poweron;
 * private int errorcode = 0;
 *
 */

/**
 * CODE...
 */

/**
 * Important imported packages to run this file
 */

import tme3.Controller;
import tme3.Event;
import java.io.*;
import java.util.Iterator;

interface Fixable {
  // turns Power on, fix window and zeros out error codes
  void fix ();
  // logs to a text file in the current directory called fix.log
// prints to the console, and identify time and nature of
// the fix
  void log();
}

public class GreenhouseControls extends Controller implements Serializable {
  // added for some unique id, (I had only One problem at first, so I included)
  private static final long serialVersionUID = 4581425092843353410L;

  private boolean light = false;
  private boolean water = false;
  private String thermostat = "Day";
  private String eventsFile = "examples1.txt";


  //-------------------------------------------------------------------------
  /**
   * All these codes are done by Alvee Hassan Akash for TME03
   */


  /**
   * Instance variables to store the state.
   * names are given as they are shown in the assignment
   */
  private boolean fans;
  private boolean windowok;
  private boolean poweron;
  private int errorcode = 0;

  /**
   * Inner class FansOn
   *  - sets the fans variable
   */
  public class FansOn extends Event {
    public FansOn (long delayTime) {
      super(delayTime);
    }
    public void action() {
      /**
       * Assignment requirements done
       */
      fans = true;
      System.out.println("Fan current state : "+ fans);
    }
    public String toString() { return "Fan is on"; }
  }

  /**
   * Inner class FansOff
   *  - sets the fans variable
   */
  public class FansOff extends Event {
    public FansOff(long delayTime){
      super(delayTime);
    }
    public void action() {
      /**
       * Assignment requirements done
       */
      fans = false;
      System.out.println("Fan current state : "+ fans);
    }
    public String toString() { return "Fan is off"; }
  }

  /**
   * Inner class WindowMalfunction
   *  - sets the windowok variable
   *
   * action()
   *  - if windowok is false and set errorCode, throws exception, shutdown the system
   */
  public class WindowMalfunction extends Event {
    public WindowMalfunction(long delayTime) {
      super(delayTime);
      windowok = false;
    }
    public void action() {
      if (windowok){
        //nothing happens now when Window is okay
      }
      else { // windowok = false then goes here to set error code and throws ControllerException
        try {
          errorcode = 1;
          System.out.println("Window current state: "+ windowok);
          throw new ControllerException("Window Malfunction ");
        }
        catch (ControllerException e){
          System.out.println(e);
          shutdown(e.getMessage());
        }
      }// else
    }// action

    public String toString() {
      return "Window Malfunction";
    }
  } // WindowMalfunction

  /**
   * Inner class PowerOut
   *  - sets the poweron variable
   *
   * action()
   *  - if poweron is false and set errorCode, throws exception, shutdown the system
   */
  public class PowerOut extends Event {
    public PowerOut(long delayTime) {
      super(delayTime);
      poweron = false;
    }
    public void action() {
      if (poweron){
        //nothing happens now when Power is on
      }
      else { // poweron = false then goes here to set error code and throws ControllerException
        try {
          errorcode = 2;
          System.out.println("Power current state: "+ poweron);
          throw new ControllerException("Power Out ");
        }
        catch (ControllerException e){
          System.out.println(e);
          shutdown(e.getMessage());
        }
      }// else
    }// action
    public String toString() {
      return "Power Out";
    }
  } // PowerOut

  /**
   * Inner class PowerOn
   *  - sets the poweron variable
   *  - sets the errorCode
   *  - calls log
   *
   * log()
   *  - write down the fixed problem
   */
  public class PowerOn extends Event implements Fixable {
    public PowerOn(long delayTime) {
      super(delayTime);
      errorcode = 0;    // set errorCode back to 0
      poweron = true;   // power is back on
      System.out.println("Power current state: "+poweron);
      log();            // Writing the fix.log file what is fixing in the system
    }
    public void action() {}
    public void fix(){}
    public void log() {
      try {
        // writing in the fix.log
        WritingFile writingFile = new WritingFile(2, true);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    public String toString() { return "Power on"; }

  } // PowerOn

  /**
   * Inner class FixWindow
   *  - sets the windowok variable
   *  - sets the errorCode
   *  - calls log
   *
   * log()
   *  - write down the fixed problem
   */
  public class FixWindow extends Event implements Fixable {
    public FixWindow(long delayTime) {
      super(delayTime);
      errorcode = 0;      // set errorCode back to 0
      windowok = true;    // window is okay
      System.out.println("Window current state: "+ windowok);
      log();              // Writing the fix.log file what is fixing in the system
    }
    public void action() {}
    public void fix() {}
    public void log() {
      try {
        WritingFile writingFile = new WritingFile(1, true);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public String toString() { return "Fix Window"; }

  } // FixWindow

  /**
   * Inner class Bell.java
   *  - Calling Bell once
   *  - Calling Bell multiple times with a number of rings parameter.
   *  - Other Events will run in BETWEEN (Bing!)
   *
   * Instance variables
   *  - ringsForBell - int value
   *  - timeInterval - long - 2000 (msec) from the start then increment by 2000
   */
  public class Bell extends Event {
    private int ringsForBell;
    private long timeInterval = 2000;

    /**
     * Bell only rings for once
     * @param delayTime - long value
     */
    public Bell(long delayTime){
      super(delayTime);
    }

    /**
     * Bell rings for multiple times.
     * @param delayTime - long value
     * @param rings - int value - how many times Bells need to be called.
     */
    public Bell(long delayTime, int rings) {
      super(delayTime);
      ringsForBell = rings-1;
      for (int i = 0; i < ringsForBell; i ++){
        addEvent(new Bell(delayTime+ timeInterval));
        timeInterval +=2000;
      }
    } // Bell constructor
    public void action() {
    } // action
    public String toString() { return "Bing!"; }

  } // end of Bell class

  /**
   * Emergency shutdown happens when System faces a problem
   * @param problem - Event name as problem
   */
  public void shutdown(String problem) {
    super.shutdown(problem);
    super.isSystemDown = true;
    try {
      // writing file what causes the shutdown in the system
      WritingFile writingFile = new WritingFile(errorcode, false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  } // shutdown

  /**
   * interface fixable type classes to fix the error and start the system again.
   * @param errorcode - int value
   * @return - fixable interface type class - in this case (FixWindow or PowerOn)
   */
  public Fixable getFixable (int errorcode){
    //which returns the appropriate Fixable object to correct the error and reset the error code to zero.
    if (errorcode == 1){
      return new FixWindow(0);
    }
    else return new PowerOn(0);
  }

  /**
   * Get the GreenhouseControls error
   * @return - int value
   */
  public int getError(){
    return errorcode;
  }

  /**
   * Inner class Restore.java
   *  - restore the system from console given file (dump.out)
   *  - writes the fix.log file
   *  -
   * Note: idea taken from
   * https://www.geeksforgeeks.org/object-serialization-inheritance-java/
   */
  public static class Restore {
    public Restore(String filename){
      try {
        GreenhouseControls newGC;
        FileInputStream dumpFileName = new FileInputStream(filename);
        ObjectInputStream dumpFileIn = new ObjectInputStream(dumpFileName);

        // Method for deserialization of object
        // Reading the object from file
        newGC = (GreenhouseControls) dumpFileIn.readObject();

        dumpFileIn.close();
        dumpFileName.close();

        // Event that fixes the problem
        Event event = (Event) newGC.getFixable(newGC.getError());

        newGC.reStart();

        // Add event if you wanna see the changes in the list what is fixed
        //newGC.addEvent(event);

        // Run the rest of the events from the list
        newGC.run();

      } // end of try
      catch(IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    } // public Restore(String filename)
  } // Restore

  /**
   * Add events to the list of this current system, Events are gathered from Reading File.
   * @param className - String value of Event to be called
   * @param time - long value of Event's time length
   * @param rings - Bell's number of rings to be played
   */
  public void callEventClass(String className, long time, int rings){
    if (className.equals("Bell")){
      addEvent(new Bell(time, rings));
    }
    if (className.equals("FansOn")){
      addEvent(new FansOn(time));
    }
    if (className.equals("FansOff")){
      addEvent(new FansOff(time));
    }
    if (className.equals("LightOn")){
      addEvent(new LightOn(time));
    }
    if (className.equals("LightOff")){
      addEvent(new LightOff(time));
    }
    if (className.equals("PowerOut")){
      addEvent(new PowerOut(time));
    }
    if (className.equals("WaterOn")){
      addEvent(new WaterOn(time));
    }
    if (className.equals("WaterOff")){
      addEvent(new WaterOff(time));
    }
    if (className.equals("WindowMalfunction")){
      addEvent(new WindowMalfunction(time));
    }
    if (className.equals("ThermostatNight")){
      addEvent(new ThermostatNight(time));
    }
    if (className.equals("ThermostatDay")){
      addEvent(new ThermostatDay(time));
    }
    if (className.equals("Terminate")){
      addEvent(new Terminate(time));
    }
  } // public void callEventClass(String className, long time, int rings)

  /**
   * Inner Class Restart (Modified)
   *  - System restart GreenhouseControls and read the file from the console and detects
   *  events and time and add it to the list in "callEventClass" method.
   */
  public class Restart extends Event {
    public Restart(long delayTime, String filename) {
      super(delayTime);
      eventsFile = filename;
    }
    public void action() {
      try {
        // Reading the file from the console and storing the events and time in the container
        ReadingFile readingFile = new ReadingFile(eventsFile);
        Iterator<String> eventList = readingFile.getEventArrayList().iterator();
        Iterator<Long> timeList = readingFile.getTimeArrayList().iterator();

        // Equal or more than 2 rings means set the rings in the system for Bell class
        // otherwise set the number only 1 ring
        int rings = readingFile.getRingsOfBell();
        rings = rings >= 2 ? rings : 1;

        // reads the list and call the method to add event
        while (eventList.hasNext()){
          String className = eventList.next();
          Long time = timeList.next();
          callEventClass(className, time, rings);
        } // while

      } // try
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }

    } // action method
    public String toString() { return "Restarting system"; }

  } // end of public class Restart


  /**
   * End of Alvee Hassan Akash code
   */

  //-------------------------------------------------------------------------

  public class Terminate extends Event {
    public Terminate(long delayTime) { super(delayTime); }
    public void action() { System.exit(0); }
    public String toString() { return "Terminating";  }
  }
  public static void printUsage() {
    System.out.println("Correct format: ");
    System.out.println("  java GreenhouseControls -f <filename>, or");
    System.out.println("  java GreenhouseControls -d dump.out");
  }
  public String toString(){
    return "this is the green house control's toString method";
  }

  public class LightOn extends Event {
    public LightOn(long delayTime) { super(delayTime); }
    public void action() {
      // Put hardware control code here to
      // physically turn on the light.
      /**
       * Assignment requirements done
       */
      light = true;
    }
    public String toString() { return "Light is on"; }
  }
  public class LightOff extends Event {
    public LightOff(long delayTime) { super(delayTime);}
    public void action() {
      // Put hardware control code here to
      // physically turn off the light.
      /**
       * Assignment requirements done
       */
      light = false;
    }
    public String toString() { return "Light is off"; }
  }
  public class WaterOn extends Event {
    public WaterOn(long delayTime) { super(delayTime); }
    public void action() {
      // Put hardware control code here.
      /**
       * Assignment requirements done
       */
      water = true;
    }
    public String toString() {
      return "Greenhouse water is on";
    }
  }
  public class WaterOff extends Event {
    public WaterOff(long delayTime) { super(delayTime); }
    public void action() {
      // Put hardware control code here.
      /**
       * Assignment requirements done
       */
      water = false;
    }
    public String toString() {
      return "Greenhouse water is off";
    }
  }
  public class ThermostatNight extends Event {
    public ThermostatNight(long delayTime) {
      super(delayTime);
    }
    public void action() {
      // Put hardware control code here.
      /**
       * Assignment requirements done
       */
      thermostat = "Night";
    }
    public String toString() {
      return "Thermostat on night setting";
    }
  }
  public class ThermostatDay extends Event {
    public ThermostatDay(long delayTime) {
      super(delayTime);
    }
    public void action() {
      // Put hardware control code here.
      /**
       * Assignment requirements done
       */
      thermostat = "Day";
    }
    public String toString() {
      return "Thermostat on day setting";
    }
  }

  //---------------------------------------------------------


  public static void main(String[] args) {
    try {
      String option = args[0];
      String filename = args[1];


      if ( !(option.equals("-f")) && !(option.equals("-d")) ) {
        System.out.println("Invalid option");
        printUsage();
      }

      GreenhouseControls gc = new GreenhouseControls();

      if (option.equals("-f")) {
        gc.addEvent(gc.new Restart(0,filename));
        System.out.println();
        gc.run();
      }

      // Call the Restore to re-start the system from the dump.out file
      if (option.equals("-d")) { Restore restore = new Restore(filename); }

      // if any error codes sees, it will serialize the object
      if (gc.errorcode == 1 || gc.errorcode == 2){
        serializeObject(gc);
        System.out.println("\nSystem is exiting");
        System.exit(0);
      } // if

    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid number of parameters");
      printUsage();
    }
  } // main method

  /**
   * Serialize object to start from the same state
   * @param object - any type of object class
   */
  public static void serializeObject(Object object){
    try
    {
      // New file to dump the current state
      FileOutputStream file = new FileOutputStream("dump.out");
      ObjectOutputStream out = new ObjectOutputStream(file);

      // Method for serialization of GreenhouseControls object
      out.writeObject(object);

      // closing the file outputs
      out.close();
      file.close();

      //System.out.println("GreenhouseControls serialized");

    } // try

    catch(IOException ex)
    {
      ex.printStackTrace();
      //System.out.println("IOException is caught");
    }
  } // end of serializeObject method


} ///:~