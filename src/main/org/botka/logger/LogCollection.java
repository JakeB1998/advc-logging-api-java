/**
 * LogCollection.java
 * Programmer: Jake Botka
 * Nov 29, 2020
 *
 */
package main.org.botka.logger;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import main.org.botka.logger.log.Log;
import main.org.botka.utility.api.base.DeepCloneInstance;



/**
 * Collection of log objects.
 * Implements deep cloning interface that deep clones instances.
 * @see main.org.botka.logger.log
 * @see main.org.botka.utility.api.base.DeepCloneInstance<Log>
 * @author Jake Botka
 *
 */
public class LogCollection implements DeepCloneInstance<Log>, Serializable {
	private static final long serialVersionUID = 675262036413294129L;
	
	private List<Log> mLogCollection;
	
	/**
	 * Default constructor.
	 */
	private LogCollection() {
		this.mLogCollection = new ArrayList(0);
	}
	
	/**
	 * Constructor.
	 * @param listOfLogs List collection of logs to load into collection
	 */
	public LogCollection(List<Log> listOfLogs) {
		this();
		this.mLogCollection = new ArrayList<>(listOfLogs);
	}
	
	/**
	 * Determiens equality between two log collection objects.
	 * @param obj Log collection object
	 * @return True if equal otherwise, false
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LogCollection) {
			LogCollection logCollection = (LogCollection) obj;
			if (logCollection != null && mLogCollection != null) {
				Log[] logs = getLogsAsArray();
				if (logs != null) {
					for (int i=0; i < logs.length; i++) {
						Log log1 = logs[i];
						Log log2 = logCollection.getLog(i);
						if (log1 != null && log2 != null) {
							if (!log1.equals(log2)) {
								return false;
							}
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Gets log at index
	 * @param index Index of log
	 * @return Log at index
	 */
	public Log getLog(int index){
		return null;
	}
	
	/**
	 * Gets log by its id.
	 * @param logID Log id
	 * @return Log with {@code logID}
	 */
	public Log getLog(String logID) {
		return null;
	}
	
	/**
	 * Map of logs by its local date time.
	 * @return Date map of logs.
	 */
	public Map<LocalDateTime, Log> toDateMap(){
		return null;
	}
	
	
	/**
	 * Maps log collection to each log's local time stamp.
	 * @return Local time map of logs.
	 */
	public Map<LocalTime, Log> toTimeMap(){
		return null;
	}
	
	/**
	 * Maps log collection to each log's local date of its creation.
	 * Filters logs to only include the speicfied date {@code date}.
	 * @param date Local date object of what date that the time map should filter to.
	 * @return Local time map of logs.
	 */
	public Map<LocalTime, Log> toTimeMap(LocalDate date){
		return null;
	}

	/**
	 * Gets logs.
	 * @return Log collection as list.
	 */
	public List<Log> getLogs() {
		return this.mLogCollection;
	}
	
	
	
	/**
	 * 
	 * @return Log collection as array.
	 */
	public Log[] getLogsAsArray() {
		List<Log> arr = this.getLogs();
		return arr != null ? arr.toArray((Log[])new Object[0]) : null;
	}

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "LogCollection [mLogCollection=" + mLogCollection + "]";
	}
}
