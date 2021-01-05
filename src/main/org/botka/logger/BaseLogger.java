/**
 * BaseLogger.java
 * Programmer: Jake Botka
 * Oct 27, 2020
 *
 */
package main.org.botka.logger;

import main.org.botka.logger.log.Log;

/**
 * @author Jake Botka
 *
 */
public class BaseLogger extends Logger {

	private boolean mLogTime;
	
	/**
	 *
	 */
	public BaseLogger() {
		super();
		this.init();
	}

	/**
	 *@param logHistoryRecorder
	 */
	public BaseLogger(LogHistoryRecorder logHistoryRecorder) {
		super(logHistoryRecorder);
		this.init();
		
	}

	/**
	 *@param logRecorder
	 */
	public BaseLogger(LogRecorder logRecorder) {
		super(logRecorder);
		this.init();
		
	}
	
	private void init() {
		//this.mLogTypeMap = new LogTypeMap(0);
		if (super.getLogRecorder() == null) {
			super.setLogRecorder(LogRecorder.getDefaultLogRecorder());
		}
	}

	

	@Override
	public void log(Log<?> log) {
		LogRecorder recorder = super.getLogRecorder();
		if (recorder != null) {
			recorder.recordLog(log);
		}

	}

	

	@Override
	public void logAll(Log<?>[] logs) {
		if (logs != null) {
			for (Log<?> log : logs) {
				this.log(log);
			}
		}

	}

}
