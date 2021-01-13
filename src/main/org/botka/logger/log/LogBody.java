/**
 * File Name: LogBody.java
 * Programmer: Jake Botka
 * Date Created: Jan 4, 2021
 *
 */
package main.org.botka.logger.log;

import java.io.Serializable;

/**
 * Body of Log.
 * @author Jake Botka
 *
 */
public class LogBody implements Serializable {
	
	private static final long serialVersionUID = 7585641032696645782L;
	private String mBodyContent;
	
	/**
	 * Private constructor.
	 */
	private LogBody() {
		mBodyContent = null;
	}
	
	/**
	 * Constructor
	 * @param bodyContent Content of body.
	 */
	public LogBody(String bodyContent) {
		mBodyContent = bodyContent;
	}
	
	/**
	 * Format and retrieve body content.
	 * @return Formatted body into string.
	 */
	public String fromattedBody() {
		return null;
	}
	
	/**
	 * Get the content of the body of this log.
	 * @return Content of body.
	 */
	public String getBodyContent() {
		return mBodyContent;
	}
	
	/**
	 * Set content of the log body.
	 * @param bodyContent Content of log body.
	 */
	public void setBodyContent(String bodyContent) {
		mBodyContent = bodyContent;
	}
}
