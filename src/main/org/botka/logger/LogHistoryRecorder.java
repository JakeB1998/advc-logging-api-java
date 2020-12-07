/**
 * LogHistoryRecorder.java
 * Programmer: Jake Botka
 * Oct 25, 2020
 *
 */
package main.org.botka.logger;

import java.util.List;
import java.util.Vector;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Implementation of ILogRecorder interface that records logs to a data
 * structure.
 * 
 * @author Jake Botka
 *
 */
public class LogHistoryRecorder implements LogRecorder {

	public static LogHistoryRecorder getDefaultLogRecorder() {
		return new LogHistoryRecorder();
	}

	private Vector<Log<?>> mLogs;

	/**
	 * Default Constrcutor.
	 */
	public LogHistoryRecorder() {
		this.mLogs = new Vector<>();
	}

	/**
	 * Constructor.
	 * 
	 * @param logs Logs to be preloaded into data structure.
	 */
	public LogHistoryRecorder(@NonNull Log<?>[] logs) {
		this();
		if (logs != null) {
			for (Log<?> log : logs) {
				this.recordLog(log);
			}
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param logs Logs to be preloaded into data structure.
	 */
	public LogHistoryRecorder(@NonNull List<Log<?>> logs) {
		this((Log<?>[]) logs.toArray());
	}

	@Override
	public void clearLogs() {
		this.mLogs.clear();

	}

	/**
	 * Records log into data structure. Can not be null
	 * 
	 * @param log Log to be recorded.
	 */
	@Override
	public boolean recordLog(@NonNull Log<?> log) {
		if (log != null) {
			this.mLogs.add(log);
			return true;
		}
		return false;
	}

	/**
	 * @param logs
	 * @return
	 * 
	 */
	@Override
	public boolean recordLogs(Log<?>[] logs) {
		boolean flag = false;
		if (logs != null) {
			flag = true;
			for (Log<?> log : logs) {
				boolean result = this.recordLog(log);
				if (flag) {
					flag = result;
				}
			}
		}
		return flag;
	}

	/**
	 * Get all logs currently recorded into data structure in array format.
	 * 
	 * @return Array of logs.
	 */
	@Override
	public Log<?>[] getLogs() {
		Log<?>[] arr = new Log<?>[this.mLogs.size()];
		return this.mLogs.toArray(arr);

	}

	/**
	 * Get all logs currently recorded into data structure as a list.
	 * 
	 * @return list of logs.
	 */
	@Override
	public List<Log<?>> getLogsAsList() {
		return this.mLogs;
	}

	/**
	 * Gets a copy of the list using the LogCollection class.
	 * 
	 * @return New LogCollection copy.
	 */
	public LogCollection getLogCollectionCopy() {
		return new LogCollection(this.mLogs);
	}

}
