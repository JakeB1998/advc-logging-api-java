package main.org.botka.logger;

import java.io.Console;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;





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
	private boolean mLogTimePermission;

	public ConsoleLogger() {
		super();
		mDefinedClass = DEFAULT_CLASS;
	}
	/**
	 * 
	 */
	public ConsoleLogger(boolean logTime) {
		this.mLogTimePermission = logTime;
		
	}

	/**
	 * 
	 * @param log
	 *
	 */
	@Override
	public void log(Log<?> log) {
		this.logString(log.toString());
	}
	
	/**
	 * 
	 * @param num
	 */
	public void logLines(int num) {
		String log = "";
		if (num > 0) {
			for (int i = 0; i < num; i++) {
				log += "\n";
			}

			System.out.println(log);
		}
	}

	/**
	 * Logs string. Implemented by ILogger
	 */
	public void logString(String str) {
		this.logString(str, this.mLogTimePermission);

	}

	/**
	 * Logs string
	 * 
	 * @param str
	 * @param logTime
	 */
	public void logString(String str, boolean logTime) {
		if (logTime) {
			this.logTime();

		}
		System.out.print(str);
		System.out.println();
	}

	

	@Override
	public void logAll(Log<?>[] logs) {
		if (logs != null && logs.length > 0) {
			for (Log<?> log : logs) {
				this.logString(log.toString());
			}
		}
		
	}
	
	

}
