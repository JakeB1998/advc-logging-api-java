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
	
	public static Log createErrorLog(Class<?> source, String logContent) {
		return createErrorLog(true, source, logContent);
	}
	
	public static Log createErrorLog(boolean timeStamp, String logContent) {
		return createErrorLog(timeStamp, null, logContent);
	}
	
	public static Log createErrorLog(boolean timeStamp, Class<?> source, String logContent) {
		return new Log(new LogHeader(System.currentTimeMillis(), source, "ERROR", true), new LogBody(logContent));
	}
	
	
	
	

}
