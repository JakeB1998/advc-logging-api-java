/**
 * File Name: LogSampleData.java
 * Programmer: Jake Botka
 * Date Created: Jan 12, 2021
 *
 */
package test.org.botka.logger;

import main.org.botka.logger.log.Log;
import main.org.botka.logger.log.StringLog;

/**
 * @author Jake Botka
 *
 */
public class LogSampleData {
	public static final StringLog[] STRING_LOG_SMALL_SAMPLE = { new StringLog("First log", System.currentTimeMillis()), 
			new StringLog("First log", System.currentTimeMillis()), new StringLog("Second log", System.currentTimeMillis())}; 
}
