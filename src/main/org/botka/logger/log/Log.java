/**
 * Log.java
 * Programmer: Jake Botka
 * Oct 25, 2020
 *
 */
package main.org.botka.logger.log;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalField;
import java.util.Vector;

import javax.annotation.Nonnull;

import org.eclipse.jdt.annotation.NonNull;

import main.org.botka.logger.log.logtype.LogType;
import main.org.botka.utility.api.util.Util;

/**
 * Log class that represents information directly related to the log. processed
 * logs will be wrapped with the LogContext.java class
 * 
 * @see LogContext.java.
 * @author Jake Botka Class represents a log that contains a header and a body.
 */
public class Log implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5107531466511965752L;
	public static final boolean DEFAULT_LOG_TIME = true;
	public static final int DEFAULT_CHARACTER_PER_LINE_COUNT = 115;
	public static final LogType DEFAULT_LOG_TYPE = LogType.GENERAL;

	private LogHeader mLogHeader;
	private LogBody mLogBody;
	private int mPerLineCharacterCountLimit;
	private boolean mLogTimeFlag;

	/**
	 * Default constructor.
	 */
	public Log() {
		mLogHeader = null;
		mLogBody = null;
		mPerLineCharacterCountLimit = DEFAULT_CHARACTER_PER_LINE_COUNT;
		mLogTimeFlag = true;
	}

	/**
	 * Constructor.
	 * 
	 * @param log     The object to be logged
	 * @param logTime True if the log should contain a date time stamp.
	 */
	public Log(@NonNull String log, long timeEpoc) {
		this(log, timeEpoc, LogType.GENERAL);
	}

	/**
	 * Constructor.
	 * 
	 * @param log     The object to be logged
	 * @param logTime True if the log should contain a date time stamp.
	 */
	public Log(@Nonnull String log, long timeEpoc, LogType logType) {
		this(log, timeEpoc, logType, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param log     The object to be logged
	 * @param logTime True if the log should contain a date time stamp.
	 */
	public Log(@Nonnull String log, long timeEpoc, LogType logType, String logTag) {
		this();
		mLogHeader = new LogHeader(new LogTime(timeEpoc));
		mLogHeader.setLogType(logType);
		mLogHeader.setLogTag(new LogTag(logTag));
		mLogBody = new LogBody(log.toString());

	}

	/**
	 * Constructor.
	 * 
	 * @param logHeader Log header object.
	 * @param logBody   Log body object.
	 */
	public Log(@Nonnull LogHeader logHeader, @Nonnull LogBody logBody) {
		this();
		mLogHeader = logHeader;
		mLogBody = logBody;

	}

	/**
	 * Formats log into a formatted string format.
	 * 
	 * @return String of the log formatted.
	 */
	private String formatLog() {
		String[] lines = formatLogsIntoLines();
		String formattedLog = "";
		if (lines != null) {
			for (String str : lines) {
				formattedLog += "\n".concat(str);
			}
			return formattedLog;
		} else {
			formattedLog = getFormattedLog();
			return formattedLog;
		}

	}
	
	/**
	 * Equality determined by calling the equal method on the log's log body object and log header object.
	 * @param other
	 * @return
	 * @see LogBody
	 * @see LogHeader
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}	
		if (this == other) {
			return true;
		}
		
		Log log = (Log) other;
		return log.getLogHeader() != null && log.getLogBody() != null 
				? log.getLogHeader().equals(this.getLogHeader()) && log.getLogBody().equals(this.getLogBody()) : false;
		
	}

	/**
	 * Formats logs into formated lines that can be used for various purpose.
	 * Example: formated lines for logging to a file.
	 * 
	 * @return Array of formatted lines for the log.
	 */
	public String[] formatLogsIntoLines() {
		String[] result = null;
		String logContents = getFormattedBody();
		// check if log is going to be multiple lines.
		if (logContents.length() > mPerLineCharacterCountLimit) {
			Vector<String> workingResult = new Vector<>();
			boolean flag = false;
			while (logContents.length() > mPerLineCharacterCountLimit) {
				String limit = flag == true ? "" : getFormattedHeader();
				int offset = mPerLineCharacterCountLimit - limit.length();
				String content = logContents.substring(0, offset > 0 ? offset : limit.length());
				int parseIndex = -1;
				// find last whitespace so word do not cut off or wrap.
				int index = content.lastIndexOf(" ");
				if (index >= 0) {
					content = content.substring(0, index);
					parseIndex = index;
				}
				workingResult.add(limit + content);
				logContents = parseIndex != -1 ? logContents.substring(parseIndex)
						: logContents.substring(0, mPerLineCharacterCountLimit);
				if (!flag) {
					flag = true;
				}
			}
			if (logContents.length() > 0) {
				if (!flag) {
					workingResult.add(generateSpaces(mLogHeader.getLogTime().getCharacterCount()) + logContents);
				} else {
					workingResult.add(logContents);
				}
			}
			result = workingResult.toArray(new String[0]);

		} else {
			result = new String[1];
			result[0] = getFormattedHeader() + logContents;
		}

		return result;
	}

	/**
	 * Generates a space in string format.
	 * 
	 * @param count number of spcaes.
	 * @return String full of white spaces with the length equal to {@code count}.
	 */
	public String generateSpaces(int count) {
		String spaces = "";
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				spaces += " ";
			}
		}
		return spaces;
	}

	/**
	 * Gets this log as a string. This is the log body content.
	 * 
	 * @return String log.
	 */

	public String getLog() {
		return mLogBody != null ? mLogBody.getBodyContent() : null;
	}

	/**
	 * Gets the log time object associated with this log.
	 * 
	 * @return Log time object.
	 */
	public LogTime getLogTime() {
		return mLogHeader != null ? mLogHeader.getLogTime() : null;
	}

	/**
	 * Gets the log header object associated with this log
	 * 
	 * @return Log header object.
	 */
	public LogHeader getLogHeader() {
		return mLogHeader;
	}

	/**
	 * Gets the log body object associated with this log.
	 * 
	 * @return Log body object.
	 */
	public LogBody getLogBody() {
		return mLogBody;
	}

	/**
	 * 
	 * @return
	 */
	public LogType getLogType() {
		return mLogHeader.getLogType();
	}

	/**
	 * 
	 * @return
	 */
	public String getFormattedLog() {
		return formatLog();
	}

	/**
	 * Gets the header to this log.
	 * 
	 * @return Log header.
	 *
	 */
	public String getFormattedHeader() {
		return mLogHeader.getFormattedHeader();
	}

	/**
	 * Gets the string representation of the object accosiated with this log
	 * 
	 * @return String representation of the object accosiated with this log.
	 *
	 */
	public String getFormattedBody() {
		return getLogBody() != null ? getLogBody().getBodyContent() : null;
	}

	/**
	 * @return
	 * 
	 */
	@Override
	public String toString() {
		return "\nLog [mLogHeader=" + mLogHeader + ", mPerLineCharacterCountLimit=" + mPerLineCharacterCountLimit
				+ ", mLogTimeFlag=" + mLogTimeFlag + "]";
	}

}
