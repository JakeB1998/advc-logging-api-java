/**
 * LogRecorder.java
 * Programmer: Jake Botka
 * Oct 25, 2020
 *
 */
package main.org.botka.logger;

import java.util.List;

import main.org.botka.logger.log.Log;

/**
 * Interface for recording logs.
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

	public default boolean recordLogs(List<?> logs) {
		if (logs != null) {
			return this.recordLogs(logs.toArray(new Log<?>[0]));
		}
		return false;
	}

	public default int getLogCount() {
		Log<?>[] arr = getLogs();
		return arr != null ? arr.length : 0;
	}

	public void clearLogs();

	public boolean recordLog(Log<?> log);

	public boolean recordLogs(Log<?>[] logs);

	public Log<?>[] getLogs();

	public List<Log<?>> getLogsAsList();

}
