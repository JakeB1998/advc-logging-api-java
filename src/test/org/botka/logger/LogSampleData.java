/**
 * File Name: LogSampleData.java
 * Programmer: Jake Botka
 * Date Created: Jan 12, 2021
 *
 */
package test.org.botka.logger;

import java.io.File;

import main.org.botka.logger.log.Log;
import main.org.botka.utility.api.util.FileUtil;


/**
 * @author Jake Botka
 *
 */
public class LogSampleData {
	public static final File LOG_TEST_FILE = FileUtil.findOrCreate("testlogger.LOG");
	public static final Log[] STRING_LOG_SMALL_SAMPLE = { new Log("First log", System.currentTimeMillis()), 
			new Log("First log", System.currentTimeMillis()), new Log("Second log", System.currentTimeMillis())}; 
}
