/**
 * File Name: LoggerMap.java
 * Programmer: Jake Botka
 * Date Created: Jan 26, 2021
 *
 */
package main.org.botka.logger;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author Jake Botka
 *
 */
public class LoggerMap<K> {
	private final Hashtable<K, List<Logger>> LOGGER_MAP;

	/**
	 * Default constructor.
	 */
	public LoggerMap() {
		LOGGER_MAP = new Hashtable<>();
	}
	
	
	/**
	 * Constructor.
	 * @param logMap log map to copy
	 */
	public LoggerMap(Map<K, List<Logger>> logMap) {
		this();
		if (logMap != null) {
			LOGGER_MAP.putAll(logMap);
		}
	}


	/**
	 * Registers logger into map.
	 * @param key Key to logger
	 * @param logger Logger
	 */
	public void registerLogger(K key, Logger logger) {
		if (key != null && logger != null) {
			if (!LOGGER_MAP.containsKey(key)) {
				LOGGER_MAP.put(key, new ArrayList<>(0));
			}
			List<Logger> loggersList = LOGGER_MAP.get(key);
			if (loggersList != null) {
				if (loggersList != null) {
					if (!loggersList.contains(logger)) {
						loggersList.add(logger);
					}
				}
			}
		}
	}
	
	/**
	 * Register logger into map.
	 * @param key Key to logger
	 * @param loggers Array of logger objects
	 */
	public void registerLoggers(K key, Logger[] loggers) {
		if (key != null && loggers != null) {
			if (!LOGGER_MAP.containsKey(key)) {
				LOGGER_MAP.put(key, new ArrayList<>(0));
			}
			List<Logger> loggersList = LOGGER_MAP.get(key);
			if (loggersList != null) {
				for (Logger logger : loggers) {
					if (loggersList != null) {
						if (!loggersList.contains(logger)) {
							loggersList.add(logger);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Checks if logger exist via its key.
	 * @param key Key to logger
	 * @return True if logger is registered in logger map, otherwise false
	 */
	public boolean hasLogger(K key) {
		return LOGGER_MAP != null ? LOGGER_MAP.containsKey(key) && LOGGER_MAP.get(key) != null : false;
	}
	
	/**
	 * Gets first logger in the array of loggers mapped to the key.
	 * @param key Key to logger array
	 * @return First logger object in array, if non is present then is null
	 */
	public Logger getLogger(K key) {
		if (LOGGER_MAP != null) {
			if (LOGGER_MAP.contains(key)) {
				List<Logger> list = LOGGER_MAP.get(key);
				if (list != null && list.size() > 0) {
					return list.get(0);
				}
			}
		}
		return null;
	}
	
	/**
	 * Get a list of loggers associated with key.
	 * @param key Key to list of loggers
	 * @return List of loggers accosiated with the key
	 */
	public List<Logger> getLoggers(K key){
		return LOGGER_MAP != null && LOGGER_MAP.contains(key) ? LOGGER_MAP.get(key) : null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Hashtable<K, List<Logger>> getLoggerMap(){
		return LOGGER_MAP;
	}

}
