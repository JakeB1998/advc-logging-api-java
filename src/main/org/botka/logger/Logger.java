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

import main.org.botka.logger.log.Log;
import main.org.botka.logger.log.logtype.ActiveLogTypes;
import main.org.botka.logger.log.logtype.LogType;
import main.org.botka.utility.api.exceptions.NotImplementedYetException;


/**
 * Abstract class that represents the manager to handle logging data.
 *
 * @author Jake Botka
 *
 */
public abstract class Logger {

	public static boolean globalDebugLogging = true;
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
	
	public abstract void log(Log log);
	
	public abstract void logAll(Log[] logs);
	
	/**
	 * Clears error
	 */
	private void clearError() {
		this.mError = false;
		this.mErrorMessage = null;
	}
	
	/**
	 * 
	 * @return True if error is present. Otherwise false.
	 */
	public boolean hasError() {
		return this.mError;
	}

	

	/**
	 * 
	 * @param log
	 */
	public void log(String log) {
		this.log(new Log(log, System.currentTimeMillis()));
	}
	
	

	
	/**
	 * 
	 * @param logs
	 */
	public void logAll(String[] logs) {
		if (logs != null) {
			Log[] logArray = new Log[logs.length];
			for (int i = 0; i < logs.length; i++) {
				logArray[i] = new Log(logs[i], System.currentTimeMillis());
			}
		}
	}

	

	
	/**
	 * @param mError the mError to set
	 */
	public void setError(boolean error, String errorMessage) {
		this.mError = error;
		this.mErrorMessage = errorMessage;
	}
	
	/**
	 * 
	 * @param logType Log type.
	 */
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
	
	/**
	 * 
	 * @param logTypes
	 */
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
	public static class Console {
		public static final Class<?> DEFAULT_LOG_CLASS = Console.class;
		
		private static String handleSeperation(boolean firstItem) {
			if (!firstItem) {
				return ", ";
			}
			return "";
		}
		/**
		 * 
		 * @param message Log message.
		 */
		public static void log(String message) {
			log(true,message);
		}

		/**
		 * 
		 * @param message
		 * @param logTime True if time is to be logged in header.
		 */
		public static void log(boolean logTime,String message) {
			log(logTime, null, message);
		}

		/**
		 * 
		 * @param c Source class of the log.
		 * @param message Log message.
		 */
		public static void log(Class<?> c, String message) {
			log(true, c, message);
		}

		/**
		 * @param logTime True if time is to be logged in header.
		 * @param c Source class of the log.
		 * @param message Log message.
		 */
		public static void log(boolean logTime, Class<?> c, String message) {
			if (message != null) {
				boolean firstFlag = true;
				StringBuilder log = new StringBuilder("");
				 log.append("[");
				if (logTime) {
					log.append(handleSeperation(firstFlag) + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
					firstFlag = false;
				}
				if (c != null) {
					log.append(handleSeperation(firstFlag) + c.getName());
					firstFlag = false;
				}
				else {
					log.append(handleSeperation(firstFlag) + DEFAULT_LOG_CLASS.getName());
					firstFlag = false;
				}
				log.append("] ");
				log.append(message);
				System.out.println(log.toString());
			} else {
				logError(true, Logger.Console.class, "Param message was null thus the log was not conducted");
			}
		}
		
		
		/**
		 * 
		 * @param message Log message.
		 */
		public static void logError(String message) {
			logError(true,message);
		}

		/**
		 * 
		 * @param message
		 * @param logTime True if time is to be logged in header.
		 */
		public static void logError(boolean logTime,String message) {
			logError(logTime, null, message);
		}

		/**
		 * 
		 * @param c Source class of the log.
		 * @param message Log message.
		 */
		public static void logError(Class<?> c, String message) {
			logError(true, c, message);
		}

		/**
		 * @param logTime True if time is to be logged in header.
		 * @param c Source class of the log.
		 * @param message Log message.
		 */
		public static void logError(boolean logTime, Class<?> c, String message) {
			if (message != null) {
				boolean firstFlag = true;
				StringBuilder log = new StringBuilder("");
				 log.append("[");
				if (logTime) {
					log.append(handleSeperation(firstFlag) + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
					firstFlag = false;
				}
				if (c != null) {
					log.append(handleSeperation(firstFlag) + c.getName());
					firstFlag = false;
				}
				else {
					log.append(handleSeperation(firstFlag) + DEFAULT_LOG_CLASS.getName());
					firstFlag = false;
				}
				log.append("] ");
				log.append(message);
				System.err.println((log.toString()));
			} else {
				logError(true, Logger.Console.class, "Param message was null thus the log was not conducted");
			}
		}
	}
}
