/**
 * LogRecorder.java
 * Programmer: Jake Botka
 * Oct 25, 2020
 *
 */
package main.org.botka.logger;

import java.util.List;

import javax.annotation.Nonnull;

import main.org.botka.logger.log.Log;

/**
 * Interface for recording logs.
 * 
 * @author Jake Botka
 *
 */
public interface LogRecorder {

	/**
	 * @return the defaultLogRecorder A Default implementation using the class
	 *         LogRecorder
	 */
	public static LogRecorder getDefaultLogRecorder() {
		return LogHistoryRecorder.getDefaultLogRecorder();
	}

	/**
	 * Records logs in param {@code logs}.
	 * 
	 * @param logs List of logs to be recorded into the history.
	 * @return True if logs were recorded, otherwise false.
	 */
	public default boolean recordLogs(@Nonnull List<?> logs) {
		if (logs != null) {
			return this.recordLogs(logs.toArray(new Log[0]));
		}
		return false;
	}

	/**
	 * Get a length count of the number of logs in the recorder. If the recorder is
	 * null will return -1.
	 * 
	 * @return number of logs in recorder
	 */
	public default int getLogCount() {
		Log[] arr = getLogs();
		return arr != null ? arr.length : -1;
	}

	/**
	 * 
	 */
	public void clearLogs();

	/**
	 * 
	 * @param log Log
	 * @return True if log was recorded, otherwise false
	 */
	public boolean recordLog(Log log);

	/**
	 * Record an array of logs. The array can not be null.
	 * 
	 * @param logs Array of logs, non null
	 * @return
	 */
	public boolean recordLogs(@Nonnull Log[] logs);

	/**
	 * Gets an array of logs.
	 * 
	 * @return Array of logs.
	 */
	public Log[] getLogs();

	/**
	 * Gets the logs as a list.
	 * 
	 * @return List of logs in recorder
	 */
	public List<Log> getLogsAsList();

}
