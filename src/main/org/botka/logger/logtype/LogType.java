/**
 * File Name: LogType.java
 * Programmer: Jake Botka
 * Date Created: Dec 7, 2020
 *
 */
package main.org.botka.logger.logtype;

import main.org.botka.logger.Equality;

/**
 * Interface to represent log types that can be both custom or predefined by this API.
 * @author Jake Botka
 *
 */
public interface LogType extends Comparable<LogType> , Equality<LogType>{

	public static final LogType GENERAL = new GeneralLogType();
	public static final LogType Error = new ErrorLogType();
	public static final LogType Info = new InfoLogType();
	
	/**
	 * @See CustomLogType.java
	 * @return True if the log type is customly defined and implemented by a programmer, otherwise false if it is a logtype defined in the API.
	 */
	public default boolean isCustomType() {
		return false;
	}
	
	/**
	 * Gets the log type in string format. This can be used for many different reasons.
	 * Some reasons include: filtering logs bassed of of the active log type(s) in the logger object and/or the log recorder object.
	 * @return Log type in string format.
	 */
	public String getLogTypeString();
}
