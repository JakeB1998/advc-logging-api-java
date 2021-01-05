/**
 * File Name: LogContext.java
 * Programmer: Jake Botka
 * Date Created: Jan 5, 2021
 *
 */
package main.org.botka.logger.log;

import java.time.LocalDateTime;

/**
 * @author Jake Botka
 *
 */
public class LogContext {
	
	public static LogContext createContext(Log<?> log) {
		return new LogContext(log);
	}

	private LocalDateTime mCreatedDateTime;
	private LocalDateTime mLastModifiedDateTime;
	private Log<?> mLog;
	
	
	public LogContext() {
		mCreatedDateTime = null;
		mLastModifiedDateTime = null;
		mLog = null;
	}
	
	public LogContext(Log<?> log) {
		mCreatedDateTime = LocalDateTime.now();
		mLastModifiedDateTime = mCreatedDateTime;
		mLog = log;
	}
	
	
	
}
