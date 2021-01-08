/**
 * Log.java
 * Programmer: Jake Botka
 * Oct 25, 2020
 *
 */
package main.org.botka.logger.log;

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
 * Log class that represents information directly related to the log. proccessed logs will be wrapped with the LogContext.java class
 * @see LogContext.java.
 * @author Jake Botka Class represents a log that contains a header and a body.
 */
public class Log<T> {
	
	public static final boolean DEFAULT_LOG_TIME = true;
	public static final int DEFAULT_CHARACTER_PER_LINE_COUNT = 150;
	public static final LogType DEFAULT_LOG_TYPE = LogType.GENERAL;

	private LogHeader mLogHeader;
	private LogBody mLogBody;
	private int mPerLineCharacterCountLimit;
	private Object mLoggedObject;
	private String mFormattedLog;
	private boolean hasPunctuation;
	private boolean mLogTimeFlag;

	

	/**
	 * Default constructor.
	 */
	public Log() {
		mLogHeader = new LogHeader();
		mLogBody = new LogBody(null);
		mFormattedLog = "";
		mPerLineCharacterCountLimit = DEFAULT_CHARACTER_PER_LINE_COUNT;
		mLogHeader.setLogType(DEFAULT_LOG_TYPE);
		mLogTimeFlag = true;
	}

	/**
	 * Constructor.
	 * 
	 * @param log     The object to be logged
	 * @param logTime True if the log should contain a date time stamp.
	 */
	public Log(@NonNull T log) {
		this(log, LogType.GENERAL);
	}

	/**
	 * Constructor.
	 * 
	 * @param log     The object to be logged
	 * @param logTime True if the log should contain a date time stamp.
	 */
	public Log(@Nonnull T log, LogType logType) {
		this();
		mLogHeader.setLogType(logType);
		Util.checkNullArgumentAndThrow(log);
		mLoggedObject = log;
		if (mLogTimeFlag) {
			String timeStamp = LogTime.getFormatedDateTime();
			if (mLogHeader.getLogTime() == null) {
				mLogHeader.setLogTIme(new LogTime(timeStamp));
			} else {
				mLogHeader.getLogTime().setTimeStamp(timeStamp);
			}
		}
		checkPunctiation();
		mLogBody.setBodyContent(log.toString());
	}

	/*
	 * Checks if log has punctuation towards the end of the line so it does not cut
	 * off punctuation to the following line.
	 */
	private void checkPunctiation() {
		// Do not need to check null as there is no setter and null checks happen at
		// constructions.
		String logContents = mLoggedObject.toString();
		if (logContents != null) {
			char[] punctiationSet = { ',', '?', '!' };
			boolean flag = false;
			for (char c : logContents.toCharArray()) {
				for (char punc : punctiationSet) {
					if (punc == c) {
						flag = true;
						break;
					}
					if (flag) {
						break;
					}
				}
			}
			hasPunctuation = flag;
		}
	}

	/**
	 * Formats log into a formatted string format.
	 */
	private String formatLog() {
		String[] lines = formatLogsIntoLines();
		if (lines != null) {
			for (String str : lines) {
				System.out.println(String.valueOf(str));
				mFormattedLog += "\n".concat(str);
			}
			return mFormattedLog;
		} else {
			mFormattedLog = getFormattedLog();
			return mFormattedLog;
		}
		
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
				//generateSpaces(mLogHeader.getLogTime().getCharacterCount())
				String limit = flag == true ? "" : getFormattedHeader();
				String content = logContents.substring(0, mPerLineCharacterCountLimit);
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
				if (flag) {
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
	 * Gets this log
	 * 
	 * @return Log.
	 */
	@SuppressWarnings("unchecked")
	public T getLog() {
		return (T) mLoggedObject;
	}

	/**
	 * 
	 * @return
	 */
	public LogTime getLogTime() {
		return mLogHeader != null ? mLogHeader.getLogTime() : null;
	}

	/**
	 * 
	 * @return
	 */
	public LogHeader getLogHeader() {
		return mLogHeader;
	}
	
	/**
	 * 
	 * @return
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
		return " " + mLoggedObject.toString();
	}

	/**
	 * @return
	 * 
	 */
	@Override
	public String toString() {
		return "Log [mLogHeader=" + mLogHeader + ", mPerLineCharacterCountLimit=" + mPerLineCharacterCountLimit
				+ ", mLoggedObject=" + mLoggedObject + ", mFormattedLog=" + mFormattedLog + ", hasPunctuation="
				+ hasPunctuation + ", mLogTimeFlag=" + mLogTimeFlag + "]";
	}

}
