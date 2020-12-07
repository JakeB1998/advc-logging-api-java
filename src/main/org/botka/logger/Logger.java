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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import org.eclipse.jdt.annotation.NonNull;

import main.org.botka.logger.logtype.ActiveLogTypes;
import main.org.botka.logger.logtype.LogType;
import main.org.botka.utility.api.exceptions.NotImplementedYetException;


/**
 * Abstract class that represents the manager to handle logging data.
 *
 * @author Jake Botka
 *
 */
public abstract class Logger {

	private LogRecorder mLogRecorder;
	private ActiveLogTypes mActiveLogTypes;
	private volatile boolean mError;
	private String mErrorMessage;
	/**
	 * Default Constructor.
	 */
	public Logger() {
		this.mLogRecorder = null;
		this.mErrorMessage = null;
		this.mActiveLogTypes = null;
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
	
	public void setLogType(@Nullable LogType logType) {
		if (logType == null) {
			this.mActiveLogTypes.clearSet();
			this.mActiveLogTypes.addLogType(logType);
		}
		else {
			this.mActiveLogTypes.clearSet();
		}
		
	}
	
	/**
	 * Sets active log types. This is not the same as the active logtypes in the recorder
	 * @param logTypes Array of logtypes which values can not be null. However the array itself can be null.
	 */
	public void setActiveLogTypes(@Nullable LogType[] logTypes) {
		if (logTypes == null) {
			this.setLogType(null);
		} else {
			this.mActiveLogTypes.setActiveLogTypes(logTypes);
		}
	}
	
	public void setActiveLogTypes(@Nullable Collection<LogType> logTypes) {
		if (logTypes == null) {
			this.setLogType(null);
		} else {
			this.mActiveLogTypes.setActiveLogTypes(logTypes.toArray(new LogType[0]));
		}
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
