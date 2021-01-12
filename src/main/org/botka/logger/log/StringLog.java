/**
 * File Name: StringLog.java
 * Programmer: Jake Botka
 * Date Created: Jan 12, 2021
 *
 */
package main.org.botka.logger.log;

import org.eclipse.jdt.annotation.NonNull;

import main.org.botka.logger.log.logtype.LogType;

/**
 * @author Jake Botka
 *
 */
public class StringLog extends Log<String> {

	/**
	 * 
	 */
	private StringLog() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param log
	 * @param timeEpoc
	 */
	public StringLog(@NonNull String log, long timeEpoc) {
		super(log, timeEpoc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param log
	 * @param timeEpoc
	 * @param logType
	 */
	public StringLog(String log, long timeEpoc, LogType logType) {
		super(log, timeEpoc, logType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param log
	 * @param timeEpoc
	 * @param logType
	 * @param logTag
	 */
	public StringLog(String log, long timeEpoc, LogType logType, String logTag) {
		super(log, timeEpoc, logType, logTag);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param logHeader
	 * @param logBody
	 */
	public StringLog(LogHeader logHeader, LogBody logBody) {
		super(logHeader, logBody);
		// TODO Auto-generated constructor stub
	}

}
