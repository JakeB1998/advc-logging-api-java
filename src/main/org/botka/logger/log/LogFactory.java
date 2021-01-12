/**
 * File Name: LogFactory.java
 * Programmer: Jake Botka
 * Date Created: Jan 11, 2021
 *
 */
package main.org.botka.logger.log;

/**
 * Factory to statically create log objects.
 * @author Jake Botka
 *
 */
public final class LogFactory {
	
	private LogFactory() {
		
	}
	
	public static <T> Log<T> createLog(T log){
		return null;
	}
	
	public static <T> Log<T> createLog(LogHeader logHeader, LogBody logBody){
		return new Log<T>(logHeader,logBody);
	}
	
	

}
