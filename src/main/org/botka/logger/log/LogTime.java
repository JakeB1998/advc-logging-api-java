/**
 * File Name: LogTime.java
 * Programmer: Jake Botka
 * Date Created: Jan 4, 2021
 *
 */
package main.org.botka.logger.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class represents time stamp of a log.
 * @author Jake Botka
 *
 */
public class LogTime {
	public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	private String mTimeStamp;
	private int mTimeStampCharacterCount;
	private boolean mFormatted;
	
	/**
	 * Fromats a DateTime object into a string. Uses default formatting "ISO_LOCAL_DATE_TIME"
	 * @param dateTime DateTime object used.
	 * @return DateTime formatted into string.
	 * @see DateTimeFormatter.ISO_LOCAL_DATE_TIME
	 */
	public static String formatDateTime(LocalDateTime dateTime) {
		if (dateTime != null) {
			String str = "[";
			str += dateTime.format(DEFAULT_DATE_TIME_FORMAT);
			int index = str.indexOf("T");
			if (index != -1 && index + 1 <= str.length()) {
				str = str.substring(0, index) + " " + str.substring(index + 1);
			}
			str += "]";
			return str;
		}
		return null;
	}
	
	/**
	 * 
	 * @return DateTime formatted into string.
	 */
	public static String getFormatedDateTime() {
		return formatDateTime(LocalDateTime.now());
	}
	
	/**
	 * Default constructor.
	 */
	public LogTime() {
		mTimeStamp = null;
	}
	
	/**
	 * 
	 * @param formattedTime
	 */
	public LogTime(String formattedTime) {
		mTimeStamp = formattedTime;
		if (mTimeStamp != null) {
			mTimeStampCharacterCount = mTimeStamp.length();
		}
	}
	
	/**
	 * 
	 * @return True if timestamp is formatted. Otherwise false.
	 */
	public boolean isFormatted() {
		return mFormatted;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTimeStamp() {
		return mTimeStamp;
	}
	
	/**
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		mTimeStamp = timeStamp;
		if (mTimeStamp != null) {
			mTimeStampCharacterCount = mTimeStamp.length();
		} else {
			mTimeStampCharacterCount = 0;
		}
	}
	
	public int getCharacterCount() {
		return mTimeStampCharacterCount;
	}

}
