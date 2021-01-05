/**
 * File Name: CustomLogType.java
 * Programmer: Jake Botka
 * Date Created: Dec 7, 2020
 *
 */
package main.org.botka.logger.log.logtype;

/**
 * Interface for custom log type implementations.
 * @author Jake Botka
 *
 */
public interface CustomLogType {

	/**
	 * 
	 * @return True by default if you are implementing this interface,
	 */
	public default boolean isCustomType() {
		return true;
	}
}
