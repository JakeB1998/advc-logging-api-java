/**
 * LogCollection.java
 * Programmer: Jake Botka
 * Nov 29, 2020
 *
 */
package main.org.botka.logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;



/**
 * @author Jake Botka
 *
 */
public class LogCollection implements main.org.botka.logger.serialization.DeepCloneInstance<Log<?>>, Serializable {
	private static final long serialVersionUID = 675262036413294129L;
	
	private List<Log<?>> mLogCollection;
	
	private LogCollection() {
		this.mLogCollection = new ArrayList(0);
	}
	/**
	 * 
	 */
	public LogCollection(List<Log<?>> listOfLogs) {
		this();
		this.mLogCollection = new ArrayList<>(listOfLogs);
	}

	/**
	 * 
	 * @return Log collection as list.
	 */
	public List<Log<?>> getLogs() {
		return this.mLogCollection;
	}
	
	
	
	/**
	 * 
	 * @return Log collection as array.
	 */
	public Log<?>[] getLogsAsArray() {
		List<Log<?>> arr = this.getLogs();
		return arr != null ? arr.toArray((Log<?>[])new Object[0]) : null;
	}
}
