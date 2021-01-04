/**
 * File Name: LogHeader.java
 * Programmer: Jake Botka
 * Date Created: Jan 4, 2021
 *
 */
package main.org.botka.logger;

import main.org.botka.logger.logtype.LogType;

/**'Header of a log. Ussualy defining variables such as time.
 * @author Jake Botka
 *
 */
public class LogHeader {

	private LogTime mLogTime;
	private LogType mLogType;
	
	/**
	 * Default constructor.
	 */
	public LogHeader() {
		mLogTime = null;
		mLogType = null;
	}
	
	/**
	 * 
	 * @return Logtime object.
	 */
	public LogTime getLogTime() {
		return mLogTime;
	}
	
	/**
	 * 
	 * @param logTime
	 */
	public void setLogTIme(LogTime logTime) {
		mLogTime = logTime;
	}
	
	/**
	 * 
	 * @return Logtype object.
	 */
	public LogType getLogType() {
		return mLogType;
	}
	
	/**
	 * Gets the header as a formatted string.
	 * @return Formated header.
	 */
	public String getFormattedHeader() {
		return  "[" + mLogTime.getTimeStamp() + mLogType.getLogTypeString() + "]";
	}
	
	/**
	 * 
	 * @param logType
	 */
	public void setLogType(LogType logType) {
		mLogType = logType;
	}

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "LogHeader [mLogTime=" + mLogTime + ", mLogType=" + mLogType + "]";
	}
	
	
}
