package main.org.botka.logger;

import java.io.Console;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import main.org.botka.logger.log.Log;
import main.org.botka.logger.log.LogHeaderFormat;
import main.org.botka.logger.log.logtype.LogType;





/**
 * 
 * Console logger that inherits from BaseLogger.class to provide implementation to log output in log format to the systems default outputstream.
 * @see BaseLoger.class
 * @see System.out
 * @author Jake Botka
 *
 */
public class ConsoleLogger extends BaseLogger implements LocalDebugLogging {
	private final Class<?> DEFAULT_CLASS = ConsoleLogger.class;
	private Class<?> mDefinedClass;
	private LogType mLogType;
	private LogHeaderFormat mLogHeaderFormat;
	private boolean  mLogTimePermission, mLogClassPermission, mGlobalDebugOverride, mLocalDebugLogPermission;

	/**
	 * Default constructor
	 */
	public ConsoleLogger() {
		super();
		mDefinedClass = DEFAULT_CLASS;
		mLogType = null;
		mLogHeaderFormat = LogHeaderFormat.DEFAULT_FROMAT;
		mLocalDebugLogPermission = true;
	}
	
	/**
	 * Constructor.
	 * @param logTime log time permission.
	 */
	public ConsoleLogger(boolean logTime) {
		mLogTimePermission = logTime;
	}
	
	/**
	 * Constructor.
	 * @param logTime log time permission.
	 * @param source
	 */
	public ConsoleLogger(boolean logTime, Class<?> source) {
		this(logTime);
		mDefinedClass = source;
	}
	
	/**
	 * 
	 * @param logTime
	 * @param source
	 * @param logType
	 */
	public ConsoleLogger(boolean logTime, Class<?> source, LogType logType) {
		this(logTime, source);
		mLogType = logType;
	}
	
	/**
	 * 
	 * @param logTime
	 * @param source
	 * @param logType
	 * @param headerFormat
	 */
	public ConsoleLogger(boolean logTime, Class<?> source, LogType logType, LogHeaderFormat headerFormat) {
		this(logTime, source, logType);
		mLogHeaderFormat = headerFormat;
	}

	/**
	 * Logs the log object to the console.
	 * @param log log object.
	 */
	@Override
	public void log(Log log) {
		boolean errorLog = log != null ? log.getLogHeader().isErrorLog() : false;
		if (errorLog) {
			System.err.println(String.valueOf(log.getFormattedLog()));
			
		} else {
			System.out.println(String.valueOf(log.getFormattedLog()));
		}
	}
	
	/**
	 * 
	 * @param logs array of logs.
	 */
	@Override
	public void logAll(Log[] logs) {
		if (logs != null && logs.length > 0) {
			for (Log log : logs) {
				if (log != null) {
					logString(log.getFormattedLog());
				} else {
					if (Logger.globalDebugLogging) {
						Logger.Console.logError(true, getClass(), "Object 'log' was null thus not logged");
					}
				}
			}
		}
		
	}
	

	/**
	 * Logs string.
	 * Logs time based on objects set value for log time permission. Time is logged using the log time permissions. 
	 * By default logs time.
	 * @param str Log string
	 */
	public void logString(String str) {
		logString(str, mLogTimePermission);
	}

	/**
	 * Logs string.
	 * 
	 * @param str string to log.
	 * @param logTime log time permission.
	 */
	public void logString(String str, boolean logTime) {
		logString(str, logTime, false);
	}
	
	/**
	 * Logs string.
	 * 
	 * @param str string to log.
	 * @param logTime log time permission.
	 */
	public void logString(String str, boolean logTime, boolean error) {
		if (error) {
			Logger.Console.logError(logTime, getClass(), str);
		} else {
			Logger.Console.log(logTime, getClass(), str);
		}
		
	}
	
	/**
	 * @return
	 * 
	 */
	@Override
	public boolean isGlobalDebugLoggingOverriden() {
		return mGlobalDebugOverride;
	}

	/**
	 * @return
	 * 
	 */
	@Override
	public boolean isDebugLoggingLocally() {
		return mLocalDebugLogPermission;
	}

	/**
	 * @param override
	 * 
	 */
	@Override
	public void setGloablLoggingOverride(boolean override) {
		mGlobalDebugOverride = override;
	}

	/**
	 * @param localDebugLogging
	 * 
	 */
	@Override
	public void setLocalDebugLogging(boolean localDebugLogging) {
		mLocalDebugLogPermission = localDebugLogging;
	}
	

	/**
	 * @return the mDefinedClass
	 */
	public Class<?> getDefinedClass() {
		return mDefinedClass;
	}

	/**
	 * @param mDefinedClass the mDefinedClass to set
	 */
	public void setDefinedClass(Class<?> definedClass) {
		this.mDefinedClass = definedClass;
	}

	/**
	 * @return the mLogType
	 */
	public LogType getLogType() {
		return mLogType;
	}

	/**
	 * @param mLogType the mLogType to set
	 */
	public void setLogType(LogType logType) {
		this.mLogType = logType;
	}

	/**
	 * @return the mLogHeaderFormat
	 */
	public LogHeaderFormat getLogHeaderFormat() {
		return mLogHeaderFormat;
	}

	/**
	 * @param mLogHeaderFormat the mLogHeaderFormat to set
	 */
	public void setLogHeaderFormat(LogHeaderFormat logHeaderFormat) {
		this.mLogHeaderFormat = logHeaderFormat;
	}

	/**
	 * @return the mLogTimePermission
	 */
	public boolean isLogTimePermission() {
		return mLogTimePermission;
	}

	/**
	 * @param mLogTimePermission the mLogTimePermission to set
	 */
	public void setmLogTimePermission(boolean logTimePermission) {
		this.mLogTimePermission = logTimePermission;
	}

	/**
	 * @return the mLogClassPermission
	 */
	public boolean isLogClassPermission() {
		return mLogClassPermission;
	}

	/**
	 * @param mLogClassPermission the mLogClassPermission to set
	 */
	public void setLogClassPermission(boolean logClassPermission) {
		this.mLogClassPermission = logClassPermission;
	}

	/**
	 * @return the dEFAULT_CLASS
	 */
	public Class<?> getDEFAULT_CLASS() {
		return DEFAULT_CLASS;
	}

	/**
	 * @return
	 * 
	 */
	@Override
	public String toString() {
		return "ConsoleLogger [DEFAULT_CLASS=" + DEFAULT_CLASS + ", mDefinedClass=" + mDefinedClass + ", mLogType="
				+ mLogType + ", mLogTimePermission=" + mLogTimePermission + ", mLogClassPermission="
				+ mLogClassPermission + "]";
	}

	

	

	
	
	

}
