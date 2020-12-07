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
	private boolean mLogTimePermission;

	/**
	 * 
	 */
	public ConsoleLogger(boolean logTime) {
		this.mLogTimePermission = logTime;
	}

	@Override
	public void log(Log<?> log) {
		this.logString(log.toString());
	}
	
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

	
	/**
	 * Logs the time in format
	 */
	@Override
	public void logTime() {
		//throw new NotImplementedYetException();
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
