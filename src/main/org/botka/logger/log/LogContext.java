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
 * Immutable context class to describe the state of a log and all of its additional data such as its meta data before serialization.
 * @author Jake Botka
 *
 */
public class LogContext implements Serializable {
	
	
	private static final long serialVersionUID = 7893505705828112444L;
	
	private boolean mStored, mStoredPersistent;


	/**
	 * Creates context using a Log object. A log context describes the state of a fully processed log.
	 * @param log Log object
	 * @return New log context object.
	 */
	public static LogContext createContext(Log log) {
		return new LogContext(log);
	}

	private LocalDateTime mCreatedDateTime, mLastModifiedDateTime;
	private Log mLog;
	
	/**
	 * Default constructor.
	 */
	public LogContext() {
		mCreatedDateTime = null;
		mLastModifiedDateTime = null;
		mLog = null;
	}
	
	/**
	 * Constructor.
	 * @param log Log object.
	 */
	public LogContext(Log log) {
		this();
		mLog = log;
		if (mLog != null) {
			mCreatedDateTime = mLog.getLogTime() != null ? mLog.getLogTime().asLocalDateTime() : null;
			mLastModifiedDateTime = mCreatedDateTime;
		}
	}
	
	/**
	 * Check if this log contect instance is equal to another. The equality is tested by testing the equals method of the loog using {@code getLog()}.
	 * if either logs are null returns false or if provided object param is not the instance of LogContext then will return false.
	 * @param obj Log context object. If not isntance will return false.
	 * @return True of this log context is equal to the log context provided with param.
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LogContext) {
			LogContext context = (LogContext) obj;
			if (context != null) {
				Log log = context.getLog();
				return log != null && this.getLog() != null ? log.equals(this.getLog()) : false;
			}
		}
		return false;
	}
	
	/**
	 * Retrieve if this log has been stored either in a persistent location such as a file 
	 * or in a volatile location such as a data structure running in the program.
	 * @return True if this log is stored, otherwise false.
	 */
	public boolean isStored() {
		return mStored;
	}
	
	/**
	 * Retrieve if the log was store in persistent storage such as a file.
	 * @return True if this log is stored in presistent storage, othwerwise false.
	 */
	public boolean isStoredPersistently() {
		return mStoredPersistent;
	}
	
	/**
	 * Get the local date time of the creation of the log.
	 * @return Local date time of the creation of the log based on its inital time stamp meta data
	 */
	public LocalDateTime getCreatedDateTime() {
		return mCreatedDateTime;
	}
	
	/**
	 * Get the local date time of the last modificationn done to the log object.
	 * @return Local date time of last modification done to log
	 */
	public LocalDateTime getLastModifiedDateTime() {
		return mLastModifiedDateTime;
	}
	
	/**
	 * Gets log object that this context describes.
	 * @return log
	 */
	public Log getLog(){
		return mLog;
	}

	/**
	 * Get a string representation of this objects instance.
	 * @return String representation of this object's instance
	 */
	@Override
	public String toString() {
		return "LogContext [mStored=" + mStored + ", mStoredPersistent=" + mStoredPersistent + ", mCreatedDateTime="
				+ mCreatedDateTime + ", mLastModifiedDateTime=" + mLastModifiedDateTime + ", mLog=" + mLog + "]";
	}
	
	
	
	
}
