/**
 * File Name: LogTime.java
 * Programmer: Jake Botka
 * Date Created: Jan 4, 2021
 *
 */
package main.org.botka.logger.log;

import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Class represents time stamp of a log.
 * @author Jake Botka
 *
 */
public class LogTime {
	public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	private long mTimeStamp;
	private String mFormattedTimeStamp;
	private int mTimeStampCharacterCount;
	private boolean mFormatted;
	
	/**
	 * Formats a DateTime object into a string. Uses default formatting "ISO_LOCAL_DATE_TIME"
	 * @param dateTime DateTime object used.
	 * @return DateTime formatted into string.
	 * @see DateTimeFormatter.ISO_LOCAL_DATE_TIME
	 */
	private static String formatDateTime(LocalDateTime dateTime) {
		if (dateTime != null) {
			String str = "";
			str += dateTime.format(DEFAULT_DATE_TIME_FORMAT);
			int index = str.indexOf("T");
			if (index != -1 && index + 1 <= str.length()) {
				str = str.substring(0, index) + " " + str.substring(index + 1);
			}
			return str;
		}
		return null;
	}
	
	/**
	 * Create an instance based on {@code epochMilliseconds} to present the time of a log.
	 * @param epochMillseconds foc seconds from recorded milliseconds from 1970. Equalivilent as System.currentTimeMillis();
	 * @return Logtime object representing the time at the specified epoch seconds.
	 * @see System.currentTimeMillis().
	 */
	public static LogTime at(long epochMillseconds) {
		return new LogTime(epochMillseconds);
	}
	
	/**
	 * Create an instance based on {@code epochMilliseconds} to present the time of a log.
	 * Only use this if you have a prerecorded time before calling this method. Otherwise use LogTime.now().
	 * @param localDateTime
	 * @return LogTime object representing the time at the specified local date time.
	 * @see LogTime.now()
	 */
	public static LogTime at(LocalDateTime localDateTime) {
		return new LogTime(localDateTime);
	}
	
	/**
	 * Create a LogTime instance where the time represents the time made at the call of this method.
	 * @return LogTime object representing the current time.
	 */
	public static LogTime now() {
		long time = System.currentTimeMillis();
		return new LogTime(time);
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
		mTimeStamp = 0;
		mFormattedTimeStamp = null;
	}
	
	/**
	 * @param epocTime
	 */
	public LogTime(long epochTime) {
		this();
		setTimeStamp(epochTime);
		setFormattedTimeStamp(formatDateTime(asLocalDateTime()));
		
	}
	
	
	/**
	 * @param localDateTime
	 */
	public LogTime(LocalDateTime localDateTime) {
		this(localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
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
	public long getTimeStamp() {
		return mTimeStamp;
	}
	
	/**
	 * 
	 * @return
	 */
	public LocalDateTime asLocalDateTime() {
		return mTimeStamp != 0 ?  Instant.ofEpochMilli(mTimeStamp).atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
	}
	
	/**
	 * 
	 * @return
	 */
	public LocalTime asLocalTime() {
		LocalDateTime localDateTime = asLocalDateTime();
		return localDateTime != null ? localDateTime.toLocalTime() : null;
	}
	
	/**
	 * 
	 * @return
	 */
	public LocalDate asLocalDate() {
		LocalDateTime localDateTime = asLocalDateTime();
		return localDateTime != null ? localDateTime.toLocalDate() : null;
	}
	
	/**
	 * 
	 * @param epochTime
	 */
	public void setTimeStamp(long epochTime) {
		mTimeStamp = epochTime;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFormattedTimeStamp() {
		return mFormattedTimeStamp == null ? formatDateTime(asLocalDateTime()) : mFormattedTimeStamp;
	}
	
	/**
	 * 
	 * 
	 * @param timeStamp
	 */
	public void setFormattedTimeStamp(String timeStamp) {
		mFormattedTimeStamp = timeStamp;
		if (mFormattedTimeStamp != null) {
			mTimeStampCharacterCount = mFormattedTimeStamp.length();
		} else {
			mTimeStampCharacterCount = 0;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCharacterCount() {
		return mTimeStampCharacterCount;
	}

}
