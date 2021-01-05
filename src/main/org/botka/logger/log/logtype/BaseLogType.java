/**
 * File Name: BaseLogType.java
 * Programmer: Jake Botka
 * Date Created: Dec 7, 2020
 *
 */
package main.org.botka.logger.log.logtype;

/**
 * @author Jake Botka
 *
 */
public abstract class BaseLogType implements LogType {

	
	@Override
	public boolean isEqual(LogType obj1, LogType obj2) {
		return obj1.getLogTypeString().equals(obj2.getLogTypeString());
	}
	
	@Override 
	public int compareTo(LogType logType) {
		return 0;
	}
}
