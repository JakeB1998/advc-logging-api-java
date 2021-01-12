/**
 * LogMap.java
 * Programmer: Jake Botka
 * Nov 29, 2020
 *
 */
package main.org.botka.logger;

import java.util.HashMap;

import main.org.botka.logger.log.Log;

/**
 * @author Jake Botka
 *
 */
public class LogMap<K> extends HashMap<K, Log> {

	public LogMap() {
		super(0);
	}
	
	
}
