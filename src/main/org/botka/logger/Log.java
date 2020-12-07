/**
 * Log.java
 * Programmer: Jake Botka
 * Oct 25, 2020
 *
 */
package main.org.botka.logger;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalField;
import java.util.Vector;

import org.eclipse.jdt.annotation.NonNull;



/**
 * @author Jake Botka Class represents a log that contains a header and a body.
 */
public class Log<T> {
	public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	public static final boolean DEFAULT_LOG_TIME = true;
	public static final int DEFAULT_CHARACTER_PER_LINE_COUNT = 100;
	public static final LogType DEFAULT_LOG_TYPE = LogType.Default;
	private LogType mLogType;
	private int mPerLineCharacterCountLimit;
	private Object mLoggedObject;
	private String mFormattedLog;
	private String mTimeStamp;
	private int mTimeStampCharacterCount;
	private boolean hasPunctuation;

	public static String getFormatedDateTime() {
		String str = "[";
		str += LocalDateTime.now().format(DEFAULT_DATE_TIME_FORMAT);
		int index = str.indexOf("T");
		if (index != -1 && index + 1 <= str.length()) {
			str = str.substring(0, index) + " " + str.substring(index + 1);
		}
		str += "]";
		return str;
	}

	/**
	 * Default constructor.
	 */
	public Log() {
		this.mTimeStamp = "";
		this.mFormattedLog = "";
		this.mPerLineCharacterCountLimit = DEFAULT_CHARACTER_PER_LINE_COUNT;
		this.mLogType = DEFAULT_LOG_TYPE;
	}

	/**
	 * Constructor. Timestamp set true as default.
	 * 
	 * @param log The object to be logged.
	 */
	public Log(T log) {
		this(log, DEFAULT_LOG_TIME);

	}
	
	/**
	 * Constructor.
	 * 
	 * @param log     The object to be logged
	 * @param logTime True if the log should contain a date time stamp.
	 */
	public Log(T log, boolean logTime) {
		this();
		Util.checkNullArgumentAndThrow(log);
		this.mLoggedObject = log;
		if (logTime) {
			this.mTimeStamp = getFormatedDateTime();
			this.mTimeStampCharacterCount = this.mTimeStamp.length();
		}
		this.checkPunctiation();
		this.formatLog();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param log     The object to be logged
	 * @param logTime True if the log should contain a date time stamp.
	 */
	public Log(T log, boolean logTime, LogType logType) {
		this(log,logTime);
		this.mLogType = logType;
	}

	/*
	 * Checks if log has punctuation towards the end of the line so it does not cut off punctuation to the following line.
	 */
	private void checkPunctiation() {
		// Do not need to check null as there is no setter and null checks happen at
		// constructions.
		String logContents = this.mLoggedObject.toString();
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
			this.hasPunctuation = flag;
		}
	}

	/**
	 * Formats log into a formatted string format.
	 */
	private void formatLog() {
		String[] lines = this.formatLogsIntoLines();
		if (lines != null) {
			for (String str : lines) {
				System.out.println(String.valueOf(str));
				this.mFormattedLog += "\n".concat(str);
			}
		} else {
			this.mFormattedLog = this.getEntireLogAsString();
		}
	}

	/**
	 * Formats logs into formated lines that can be used for various purpose.
	 * Example: formated lines for logging to a file.
	 * @return
	 */
	public String[] formatLogsIntoLines() {
		String[] result = null;
		String logContents = this.getLogBodyAsString();
		//check if log is going to be multiple lines.
		if (logContents.length() > this.mPerLineCharacterCountLimit) {
			Vector<String> workingResult = new Vector<>();
			boolean flag = false;
			while (logContents.length() > this.mPerLineCharacterCountLimit) {
				String limit = flag == true ? generateSpaces(this.mTimeStampCharacterCount) : this.getLogHeaderAsString();
				String content = logContents.substring(0, this.mPerLineCharacterCountLimit);
				int parseIndex = -1;
				// find last whitespace so word do not cut off or wrap.
				int index = content.lastIndexOf(" ");
				if (index >= 0) {
					content = content.substring(0, index);
					parseIndex = index; 
				}
				workingResult.add(limit + content);
				logContents = parseIndex != -1 ? logContents.substring(parseIndex)
						: logContents.substring(0, this.mPerLineCharacterCountLimit);
				if (!flag) {
					flag = true;
				}
			}
			if (logContents.length() > 0) {
				if (flag) {
					workingResult.add(generateSpaces(this.mTimeStampCharacterCount) + logContents);
				} else {
					workingResult.add(logContents);
				}
			}
			result = workingResult.toArray(new String[0]);
			
		} else {
			result = new String[1];
			result[0] = this.getLogHeaderAsString() + logContents;
		}
		
		return result;
	}

	/**
	 * Generates a space in string format.
	 * @param count
	 * @return Space
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
		return (T) this.mLoggedObject;
	}
	
	public LogType getLogType() {
		return this.mLogType;
	}

	/**
	 * 
	 * @return
	 */
	public String getFormattedLog() {
		return this.mFormattedLog;
	}

	/**
	 * Gets the header to this log.
	 * 
	 * @return Log header.
	 *
	 */
	public String getLogHeaderAsString() {
		return this.mTimeStamp;
	}

	/**
	 * Gets the string representation of the object accosiated with this log
	 * 
	 * @return String representation of the object accosiated with this log.
	 *
	 */
	public String getLogBodyAsString() {
		return " " + this.mLoggedObject.toString();
	}

	/**
	 * 
	 * @return String representation of the log.
	 *
	 */
	public String getEntireLogAsString() {
		return this.getLogHeaderAsString() + this.getLogBodyAsString();
	}

	/**
	 * @return
	 * 
	 */
	@Override
	public String toString() {
		return "\nLog [mLogType=" + mLogType + ", mPerLineCharacterCountLimit=" + mPerLineCharacterCountLimit
				+ "\nmLoggedObject=" + mLoggedObject + ", mFormattedLog=" + mFormattedLog + ", mTimeStamp=" + mTimeStamp
				+ "\nmTimeStampCharacterCount=" + mTimeStampCharacterCount + ", hasPunctuation=" + hasPunctuation + "]";
	}

	

}
