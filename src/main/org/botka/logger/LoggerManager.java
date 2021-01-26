/**
 * File Name: LoggerManager.java
 * Programmer: Jake Botka
 * Date Created: Jan 26, 2021
 *
 */
package main.org.botka.logger;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import main.org.botka.utility.api.base.ID;
import main.org.botka.utility.api.security.IDGenerator;

/**
 * Manager for multiple loggers that contains a map of loggers.
 * These loggers are mapped to a String and can be accessed accordingly
 * @author Jake Botka
 *
 */
public class LoggerManager {

	private final ID mID;
	private LoggerMap<String> mLoggerMap;
	
	
	
	
	/**
	 * Main constructor.
	 * @param id
	 */
	public LoggerManager(ID id) {
		if (id == null) {
			id = new ID(IDGenerator.generateSecureID());
		}
		mID = id;
		mLoggerMap = null;
	}
	
	/**
	 * 
	 * @param id ID
	 * @param logMap preexcisting log map
	 */
	public LoggerManager(ID id, LoggerMap<String> logMap) {
		this(id);
		mLoggerMap = logMap;
	}
	
	/**
	 * 
	 * @param loggerMap preexcisting logger map
	 */
	public LoggerManager(LoggerMap<String> loggerMap) {
		this (new ID(IDGenerator.generateSecureID()));
	}
	
	/**
	 * 
	 * @param logMap preexcisting log map
	 */
	public LoggerManager(Map<String, List<Logger>> logMap) {
		this(new LoggerMap<String>(logMap));
	}
	
	
	
	
	
	/**
	 * @return
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mID == null) ? 0 : mID.hashCode());
		result = prime * result + ((mLoggerMap == null) ? 0 : mLoggerMap.hashCode());
		return result;
	}

	/**
	 * Equality determined by the objects ID and its logger map.
	 * @param obj
	 * @return True if ID and logger map are equal to eachother, otherwise false
	 * @see LoggerMap
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof LoggerManager) {
			LoggerManager other = (LoggerManager) obj;
			if (other != null && other.getID() != null && other.getLoggerMap() != null 
					&& this.getID() != null && this.getLoggerMap() != null) {
				return other.getID().equals(this.getID()) && other.getLoggerMap().equals(this.getLoggerMap());
			} 
		}
		
		return false;
	}

	/**
	 * Registers logger into map.
	 * @param key Key to logger
	 * @param logger Logger
	 */
	public void registerLogger(String key, Logger logger) {
		if (mLoggerMap != null) {
			mLoggerMap.registerLogger(key, logger);
		}
	}
	
	/**
	 * Register logger into map.
	 * @param key Key to logger
	 * @param loggers Array of logger objects
	 */
	public void registerLoggers(String key, Logger[] loggers) {
		if (mLoggerMap != null) {
			mLoggerMap.registerLoggers(key, loggers);
		}
	}
	
	/**
	 * Checks if logger exist via its key.
	 * @param key Key to logger
	 * @return True if logger is registered in logger map, otherwise false
	 */
	public boolean hasLogger(String key) {
		return mLoggerMap != null ? mLoggerMap.hasLogger(key) : false;
	}
	
	/**
	 * Gets first logger in the array of loggers mapped to the key.
	 * @param key Key to logger array
	 * @return First logger object in array, if non is present then is null
	 */
	public Logger getLogger(String key) {
		return mLoggerMap != null ? mLoggerMap.getLogger(key) : null;
	}
	
	/**
	 * Get a list of loggers associated with key.
	 * @param key Key to list of loggers
	 * @return List of loggers accosiated with the key
	 */
	public List<Logger> getLoggers(String key){
		return mLoggerMap != null ? mLoggerMap.getLoggers(key) : null;
	}
	
	public ID getID() {
		return mID;
	}
	
	/**
	 * 
	 * @return
	 */
	public LoggerMap<String> getLoggerMap(){
		return mLoggerMap;
	}
	
	

	/**
	 * @return
	 * 
	 */
	@Override
	public String toString() {
		return "LoggerManager [mID=" + mID + ", mLoggerMap=" + mLoggerMap + "]";
	}
	
	

}
