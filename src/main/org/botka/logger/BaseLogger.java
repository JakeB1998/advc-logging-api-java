/**
 * BaseLogger.java
 * Programmer: Jake Botka
 * Oct 27, 2020
 *
 */
package main.org.botka.logger;

import main.org.botka.logger.log.Log;

/**
 * Base logger that adds implementation from the Logger interface
 * @see main.org.botka.logger.Logger
 * @author Jake Botka
 *
 */
public class BaseLogger extends Logger {

	
	
	/**
	 * Default constructor.
	 */
	public BaseLogger() {
		super();
		this.init();
	}

	/**
	 * @param logHistoryRecorder Log history recorder delagate object
	 */
	public BaseLogger(LogHistoryRecorder logHistoryRecorder) {
		super(logHistoryRecorder);
		this.init();
		
	}

	/**
	 * @param logRecorder Log recorder object
	 */
	public BaseLogger(LogRecorder logRecorder) {
		super(logRecorder);
		this.init();
		
	}
	
	/**
	 * 
	 */
	private void init() {
		//this.mLogTypeMap = new LogTypeMap(0);
		if (super.getLogRecorder() == null) {
			super.setLogRecorder(LogRecorder.getDefaultLogRecorder());
		}
	}

	

	/**
	 * Log a log object.
	 * @param log Log
	 * @see main.org.botka.logger.log
	 */
	@Override
	public void log(Log log) {
		LogRecorder recorder = super.getLogRecorder();
		if (recorder != null) {
			recorder.recordLog(log);
		}

	}

	

	/**
	 * Log all log objects.
	 * @param logs Array of logs
	 */
	@Override
	public void logAll(Log[] logs) {
		if (logs != null) {
			for (Log log : logs) {
				this.log(log);
			}
		}

	}

}
