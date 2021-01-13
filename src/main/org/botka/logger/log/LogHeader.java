/**
 * File Name: LogHeader.java
 * Programmer: Jake Botka
 * Date Created: Jan 4, 2021
 *
 */
package main.org.botka.logger.log;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.annotation.Nonnull;

import main.org.botka.logger.ErrorWrapper;
import main.org.botka.logger.log.logtype.LogType;
import test.org.botka.logger.FileLoggerTests;

/**'Header of a log. Ussualy defining variables such as time.
 * @author Jake Botka
 *
 */
public class LogHeader implements Serializable {
	public static final char LINE_SEP_DEFAULT = ',';
	
	private static final long serialVersionUID = 8382553458681358168L;
	private final Vector<ErrorWrapper> ERRORS = new Vector<>();
	private LogHeaderFormat mHeaderFormat;
	private boolean mLogNulls;
	private String mFormattedHeader, mLineSeperator;
	private LogTime mLogTime;
	private Class<?> mSource;
	private LogType mLogType;
	private LogTag mLogTag;
	
	
	
	/**
	 * Default constructor.
	 */
	public LogHeader() {
		mHeaderFormat = null;
		mFormattedHeader = null;
		mLineSeperator = String.valueOf(LINE_SEP_DEFAULT);
		mLogTag = null;
		mLogTime = null;
		mLogType = null;
		mSource = null;
	}
	
	/**
	 * 
	 * @param epocTime
	 * @param logTag
	 */
	public LogHeader(long epocTime, String logTag) {
		this (new LogTime(epocTime), logTag);
	}
	
	/**
	 * @param currentTimeMillis
	 * @param class1
	 * @param string
	 */
	public LogHeader(long epochTime, Class<?> source, String logTag) {
		this(new LogTime(epochTime), source, logTag);
	}
	
	/**
	 * @param currentTimeMillis
	 * @param class1
	 * @param string
	 */
	public LogHeader(long epochTime, Class<?> source, String logTag, LogHeaderFormat headerFormat) {
		this(new LogTime(epochTime), source, logTag, headerFormat);
	}

	
	/**
	 * 
	 * @param localDateTime
	 * @param logTag
	 */
	public LogHeader(LocalDateTime localDateTime, String logTag) {
		this(new LogTime(localDateTime), logTag);
	}
	
	/**
	 * 
	 * @param logTime
	 */
	public LogHeader(LogTime logTime) {
		this(logTime, null);
	}
	
	/**
	 * 
	 * @param logTime
	 * @param logTag
	 */
	public LogHeader(LogTime logTime, String logTag) {
		this(logTime, null, logTag);
	}
	
	/**
	 * 
	 * @param logTime
	 * @param logTag
	 */
	public LogHeader(LogTime logTime, Class<?> source,  String logTag) {
		this(logTime,source,logTag, new LogHeaderFormat(false, LogHeaderFormat.DEFAULT_HEADER_ORDERING));
	}
	
	/**
	 * 
	 * @param logTime
	 * @param logTag
	 */
	public LogHeader(LogTime logTime, Class<?> source,  String logTag, LogHeaderFormat headerFormat) {
		this();
		mLogTime = logTime;
		mSource = source;
		mLogTag = new LogTag(logTag);
		mHeaderFormat = headerFormat;
	}
	
	
	
	
	/**	
	 * 
	 * @param firstFlag
	 * @param strBuilder
	 * @return false
	 */
	private boolean handleFirstFlag(boolean firstFlag, final StringBuilder strBuilder) {
		if (firstFlag) {
			firstFlag = false;
		} else {
			strBuilder.append(mLineSeperator.trim() + " ");
		}
		return firstFlag;
	}
	
	private void appendHeaderItem(String appendable, String headerItem, @Nonnull StringBuilder strBuilder) {
		if (headerItem != null || mLogNulls) {
			handleFirstFlag(strBuilder.length() <= 1, strBuilder);
			
			strBuilder.append((appendable != null ? appendable : "") +  String.valueOf(headerItem));
		}
	}
	
	/**
	 * Formats headers.
	 * @return Formatted header as a string
	 */
	public String formatHeader() {
		if (ERRORS.size() == 0) {
			StringBuilder strBuilder = new StringBuilder();
			boolean firstFlag = true;
			strBuilder.append("[");
			String holder = null;
			if (mHeaderFormat == null) {
				mHeaderFormat = LogHeaderFormat.DEFAULT_FROMAT;
			}
			short[] orderIds = mHeaderFormat.getHeaderOrdering();
			//Allows re ordering of header items
			for (int i = 0; i < orderIds.length; i++) {
				short id = orderIds[i];
				switch (id) {
				case LogHeaderFormat.TIME_ID:
					holder = mLogTime != null ? mLogTime.getFormattedTimeStamp() : null;
					appendHeaderItem(null, holder, strBuilder);
					break;
				case LogHeaderFormat.CLASS_ID:
					holder = mSource != null ? mSource.getName() : null;
					appendHeaderItem( "Source Class: ", holder, strBuilder); 
					break;
				case LogHeaderFormat.LOG_TYPE_ID:
					holder = mLogType != null ? mLogType.getLogTypeString() : null;
					appendHeaderItem( "Log Type: ", holder, strBuilder); 
					break;
				case LogHeaderFormat.LOG_TAG_ID:
					holder = mLogTag != null ? mLogTag.getLogTag() : null;
					appendHeaderItem( "Log Tag: ", holder, strBuilder); 
					break;
				default:
					break;
				}
			}
			
			strBuilder.append("] ");
			return strBuilder.toString();
		}
		return null;
	}

	/**
	 * 
	 * @return True if errors are present, otherwise false.
	 */
	public boolean hasErros() {
		return ERRORS.size() > 0;
	}
	
	/**
	 * Get vector of errors present.
	 * @return Vector of errors.
	 */
	public Vector<ErrorWrapper> getErrors() {
		return ERRORS;
	}
	
	/**
	 * 
	 * @return Logtime object.
	 */
	public LogTime getLogTime() {
		return mLogTime;
	}
	
	/**
	 * 
	 * @param logTime
	 */
	public void setLogTime(LogTime logTime) {
		if (logTime != null && mLogTime != null) {
			if (!mLogTime.equals(logTime)) {
				mFormattedHeader = formatHeader();
			}
		}
		mLogTime = logTime;
	}
	
	/**
	 * 
	 * @return Logtype object.
	 */
	public LogType getLogType() {
		return mLogType;
	}
	
	/**
	 * 
	 * @param logType
	 */
	public void setLogType(LogType logType) {
		if (logType != null && mLogTime != null) {
			
			if (mLogType != null && !mLogType.equals(logType)) {
				mFormattedHeader = formatHeader();
			}
			
		}
		mLogType = logType;
	}
	
	/**
	 * @param logTag
	 */
	public void setLogTag(LogTag logTag) {
		mLogTag = logTag;

	}
	
	/**
	 * Gets the header as a formatted string.
	 * @return Formated header.
	 */
	public String getFormattedHeader() {
		if (mFormattedHeader == null) {
			mFormattedHeader = formatHeader();
		}
		return  mFormattedHeader;
	}
	
	
	
	//Add log tag getters and setters.

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "LogHeader [mLogTime=" + mLogTime + ", mLogType=" + mLogType + "]";
	}

	
	
	
}
