/**
 * File Name: LogBody.java
 * Programmer: Jake Botka
 * Date Created: Jan 4, 2021
 *
 */
package main.org.botka.logger.log;

import java.io.Serializable;
import java.util.Objects;

import main.org.botka.logger.Logger;
import main.org.botka.utility.api.base.ID;

/**
 * Body of Log.
 * @author Jake Botka
 *
 */
public class LogBody implements Serializable {
	private static final long serialVersionUID = 7585641032696645782L;
	private LogHeader mAttachedHeader;
	private String mBodyContent;
	
	/**
	 * Private constructor.
	 */
	private LogBody() {
	mAttachedHeader = null;;
		mBodyContent = null;
	}
	
	
	
	/**
	 * 
	 * @param attachedHeader
	 * @param bodyContent
	 */
	public LogBody(LogHeader attachedHeader, String bodyContent) {
		this();
		mAttachedHeader = attachedHeader;
		mBodyContent = bodyContent;
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public boolean equals(Object other) {
		if (other != null && other instanceof LogBody) {
			LogBody logBody = (LogBody) other;
			return Objects.equals(logBody.getBodyContent(), this.getBodyContent()) && Objects.equals(logBody.getID(), this.getID());
		}
		return false;
	}
	
	/**
	 * Format and retrieve body content.
	 * @return Formatted body into string.
	 */
	public String fromattedBody() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public LogHeader getLogHeader() {
		return mAttachedHeader;
	}
	
	/**
	 * 
	 * @param logHeader
	 */
	public void setLogHeader(LogHeader logHeader) {
		mAttachedHeader = logHeader;
	}
	/**
	 * 
	 * @return
	 */
	public ID getID() {
		return mAttachedHeader != null ? mAttachedHeader.getID() : null;
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
