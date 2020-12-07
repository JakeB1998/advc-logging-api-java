/*
 * File name:  Logger.java
 *
 * Programmer : Jake Botka
 * ULID: JMBOTKA
 *
 * Date: May 26, 2020
 *
 * Out Of Class Personal Program
 */
package main.org.botka.logger;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract class that represents the manager to handle logging data.
 *
 * @author Jake Botka
 *
 */
public abstract class Logger {

	private LogRecorder mLogRecorder;
	private volatile boolean mError;
	private String mErrorMessage;
	/**
	 * Default Constructor.
	 */
	public Logger() {
		this.mLogRecorder = null;
		this.mErrorMessage = null;
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param logHistoryRecorder
	 */
	public Logger(LogHistoryRecorder logHistoryRecorder) {
		this((LogRecorder)logHistoryRecorder);
	}

	/**
	 * Constructor.
	 * 
	 * @param logRecorder
	 */
	public Logger(LogRecorder logRecorder) {
		this.mLogRecorder = logRecorder;
	}
	
	public void clearError() {
		this.mError = false;
		this.mErrorMessage = null;
	}
	

	public boolean hasError() {
		return this.mError;
	}

	/**
	 * 
	 * @param log
	 * @param logTime
	 *
	 */
	public void log(String log, boolean logTime) {
		this.log(new Log<String>(log,logTime));
	}

	/**
	 * 
	 * @param log
	 *
	 */
	public void log(String log) {
		this.log(new Log<String>(log));
	}
	
	public<T> void log(T log) {
		this.log(new Log<T>(log));
	}

	public abstract void log(Log<?> log);

	

	public abstract void logTime();

	// public abstract void logLines(String[] lines);
	/**
	 * 
	 * @param logs
	 *
	 */
	public void logAll(String[] logs) {
		if (logs != null) {
			Log<String>[] logArray = new Log[logs.length];
			for (int i = 0; i < logs.length; i++) {
				logArray[i] = new Log<String>(logs[i]);
			}
		}
	}

	public abstract void logAll(Log<?>[] logs);

	
	/**
	 * @param mError the mError to set
	 */
	public void setError(boolean error, String errorMessage) {
		this.mError = error;
		this.mErrorMessage = errorMessage;
	}
	
	/**
	 * 
	 * @return
	 *
	 */
	public LogRecorder getLogRecorder() {
		return this.mLogRecorder;
	}


	/**
	 * @param defaultLogRecorder
	 * 
	 */
	public void setLogRecorder(LogRecorder logRecorder) {
		this.mLogRecorder = logRecorder;
		
	}
	

	/**
	 * Static class that allows access to static ethods to quickly log to the console.
	 * @author Jake Botka
	 *
	 */
	public static class Console{
		public static void log(String message) {
			log(null, message, false);
			
		}

		public static void log(String message, boolean logTime) {
			log(null, message, logTime);
		}

		public static void log(Class<?> c, String message) {
			log(c, message, false);
		}

		public static void log(Class<?> c, String message, boolean logTime) {

			String log = "";
			if (logTime)
				log += LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
			if (c != null)
				log += c.getName() + ": \t";
			if (message != null)
				log += message;

			System.out.println(log);
		}
	}
}
