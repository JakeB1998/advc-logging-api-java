/**
 * LogTypeMap.java
 * Programmer: Jake Botka
 * Nov 29, 2020
 *
 */
package main.org.botka.logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import main.org.botka.logger.log.Log;
import main.org.botka.logger.log.logtype.LogType;

/**
 * Map that sorts the log type into seperated list collections.
 * 
 * @author Jake Botka
 *
 */
public class LogTypeMap extends HashMap<LogType, Vector<Log>> {

	private static final long serialVersionUID = -1161398212761689224L;

	/**
	 * Default constructor. constructs with a size of 0.
	 */
	public LogTypeMap() {
		super(0);
	}

	/**
	 * Constructor.
	 * 
	 * @param size Initial size of map.
	 */
	public LogTypeMap(int size) {
		super(size);
	}

	/**
	 * Constructor. Constructs a copy of the list provided @param. Sorts this list
	 * by log type into a HashMap.
	 * 
	 * @param listOfLogs List containing logs.
	 */
	public LogTypeMap(List<Log> listOfLogs) {
		if (listOfLogs != null) {
			Iterator<Log> iterator = listOfLogs.iterator();
			while (iterator.hasNext()) {
				Log obj = iterator.next();
				LogType logType = obj.getLogType();
				if (super.containsKey(logType)) {
					Vector<Log> vector = super.get(logType);
					vector.add(obj);
				} else {
					Vector<Log> vec = new Vector<>();
					vec.add(obj);
					super.put(logType, vec);
				}
			}
		}
	}

	/**
	 * Checks if map has log type.
	 * 
	 * @param logType Log type.
	 * @return true If log map has log type, otherwise false.
	 */
	public boolean hasLogsWithType(LogType logType) {
		return super.containsKey(logType);
	}

	/**
	 * @param logType
	 * @return Log collection as list.
	 */
	public List<Log> getLogs(LogType logType) {
		return super.containsKey(logType) ? new ArrayList(super.get(logType)) : null;
	}
}
