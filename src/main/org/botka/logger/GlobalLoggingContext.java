/**
 * File Name: GlobalLoggingContext.java
 * Programmer: Jake Botka
 * Date Created: Jan 4, 2021
 *
 */
package main.org.botka.logger;

/**
 * Static class with a singular vairable that can be accessed for global logging. 
 * The logging system will log information about its interworkings if this variable is true.
 * @author Jake Botka
 *
 */
public class GlobalLoggingContext {
	public static boolean globalLogging = false;
	
	/**
	 * Set to false by default.
	 * if you want the logging system to log its own events. 
	 * Otherwise set to false. Base value is default at system initialization.
	 * @param value True if you want the logging system to log its own events. Otherwise set to default. Base value is default at system initalization.
	 */
	public static void logSystemEvents(boolean value) {
		globalLogging = value;
	}
	
}
