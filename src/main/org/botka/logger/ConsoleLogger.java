package main.org.botka.logger;

import java.io.Console;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import main.org.botka.logger.log.Log;





/**
 * 
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class ConsoleLogger extends BaseLogger {
	private final Class<?> DEFAULT_CLASS = ConsoleLogger.class;
	private Class<?> mDefinedClass;
	private boolean mLogTimePermission, mLogClassPermission;

	public ConsoleLogger() {
		super();
		mDefinedClass = DEFAULT_CLASS;
	}
	
	/**
	 * Constructor.
	 * @param logTime log time permission.
	 */
	public ConsoleLogger(boolean logTime) {
		mLogTimePermission = logTime;
	}

	/**
	 * Logs the log object to the console.
	 * @param log log object.
	 */
	@Override
	public void log(Log<?> log) {
		logString(log.getFormattedLog());
	}
	
	/**
	 * 
	 * @param logs array of logs.
	 */
	@Override
	public void logAll(Log<?>[] logs) {
		if (logs != null && logs.length > 0) {
			for (Log<?> log : logs) {
				logString(log.getFormattedLog());
			}
		}
		
	}
	

	/**
	 * Logs string.
	 * Logs time based on objects set value for log time permission.
	 */
	public void logString(String str) {
		logString(str, mLogTimePermission);
	}

	/**
	 * Logs string.
	 * 
	 * @param str string to log.
	 * @param logTime log time permission.
	 */
	public void logString(String str, boolean logTime) {
		Logger.Console.log(str, logTime);
	}

	

	
	
	

}
