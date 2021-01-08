/**
 * File Name: LogTag.java
 * Programmer: Jake Botka
 * Date Created: Jan 8, 2021
 *
 */
package main.org.botka.logger.log;

/**
 * Class represents the tag of a log.
 * @author Jake Botka
 *
 */
public class LogTag {
	private String mLogTag;
	
	private LogTag() {
		mLogTag = null;
	}
	
	/**
	 * Main constructor.
	 * @param logTag
	 */
	public LogTag(String logTag) {
		this();
		mLogTag = logTag;
	}
	public String getLogTag() {
		return mLogTag;
	}
	
	public void setLogTag(String logTag) {
		mLogTag = logTag;
	}
}
