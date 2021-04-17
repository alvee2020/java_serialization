//: innerclasses/controller/Controller.java
// The reusable framework for control systems.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

/***********************************************************************
 * Adapated for COMP308 Java for Programmer, 
 *		SCIS, Athabasca University
 *
 * Assignment: TME3
 * @author: Steve Leung
 * @date  : Oct 21, 2006
 *
 */

package tme3;
import java.io.Serializable;
import java.util.*;

// added Serializable
public class Controller implements Serializable {
  public boolean isSystemDown = false;
//  private static final long serialVersionUID = 2581425092843353410L;
  // A class from java.util to hold Event objects:
  private final List<Event> eventList = new ArrayList<Event>();
  public void addEvent(Event c) { eventList.add(c); }

  public void run() {
    while (eventList.size() > 0)
      // Make a copy so you're not modifying the list
      // while you're selecting the elements in it:
      for (Event e : new ArrayList<Event>(eventList)){
        // check if the system is down or not
        if (isSystemDown) {
          isSystemDown = false; return;
        }
        if (e.ready()) {
          System.out.println(e);
          e.action();
          eventList.remove(e);
        } // if(e.ready())
      } // for
  } // end of public void run()

  /**
   * Shutdown the system. showing the reason.
   * @param problem - string value of the problem name
   */
  public void shutdown(String problem){
    System.out.println("Emergency Shutdown !! \nReason is- " + problem +"\n");
  }

  /**
   * Resets the time of the events in the list after restoring
   * with an adding of 2000 msec as Steve said in CAS-159935-Y2M8V0
   */
  public void reStart(){
    long value = eventList.get(0).delayTime;
    for (Event e : eventList){
      e.delayTime = e.delayTime - value + 2000;
      e.start();
    } // for
  } // end of public void reStart()

} ///:~
