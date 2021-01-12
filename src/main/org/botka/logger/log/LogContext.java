/**
 * File Name: LogContext.java
 * Programmer: Jake Botka
 * Date Created: Jan 5, 2021
 *
 */
package main.org.botka.logger.log;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Context class to describe the state of a log and all of its additional data such as its meta data before serialization.
 * @author Jake Botka
 *
 */
public class LogContext implements Serializable {
	
	
	private static final long serialVersionUID = 7893505705828112444L;


	public static LogContext createContext(Log log) {
		return new LogContext(log);
	}

	private LocalDateTime mCreatedDateTime;
	private LocalDateTime mLastModifiedDateTime;
	private Log mLog;
	
	
	public LogContext() {
		mCreatedDateTime = null;
		mLastModifiedDateTime = null;
		mLog = null;
	}
	
	public LogContext(Log log) {
		mCreatedDateTime = LocalDateTime.now();
		mLastModifiedDateTime = mCreatedDateTime;
		mLog = log;
	}
	
	
	public LocalDateTime getCreatedDateTime() {
		return mCreatedDateTime;
	}
	
	public LocalDateTime getLastModifiedDateTime() {
		return mLastModifiedDateTime;
	}
	
	public Log getLog(){
		return mLog;
	}
	
	
	
	
}
