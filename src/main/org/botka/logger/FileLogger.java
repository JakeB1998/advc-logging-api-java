package main.org.botka.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jdt.annotation.NonNull;

import com.google.common.io.Files;

import main.org.botka.utility.api.util.Util;


public class FileLogger extends BaseLogger {
	private File mLoggerFile;
	private BufferedWriter mBufferedWriter;
	private FileWriter mFileWriter;
	private FilterReader mFileReader;
	
	private int mCurrentLine;
	

	private DateTimeFormatter mTimeFormat;

	private boolean mTimeStamps;

	
	public FileLogger() {
		super();
		this.mLoggerFile = null;
		this.mBufferedWriter = null;
		this.mFileWriter = null;
		this.mFileReader = null;
	}
	
	/**
	 * Constructor
	 * 
	 * @param file File to be logged to
	 */
	public FileLogger(File file) {
		this(file,true,false);
		
	}

	/**
	 * Constructor
	 * 
	 * @param file       File to be logged to
	 * @param timeStamps True if Time should be logged
	 */
	public FileLogger(File file, boolean timeStamps) {
		this(file,timeStamps,false);
		
	}

	/**
	 * Constructor
	 * 
	 * @param file       File to be logged to
	 * @param timeStamps True if time should be logged
	 * @param clearDoc   True if the document should be cleared before logging
	 */
	public FileLogger(File file, boolean timeStamps, boolean clearDoc) {
		this();
		
		this.init(file, clearDoc);
		this.mTimeStamps = timeStamps;

	}

	
	public FileLogger(String filePath) {
		this(new File(filePath));
	}
	
	/**
	 * 
	 * @param path
	 * @param timeStamps
	 */
	public FileLogger(String path, boolean timeStamps) {
		this(path);
		this.mTimeStamps = timeStamps;
	}

	/**
	 * 
	 * @param path
	 * @param timeStamps
	 */
	public FileLogger(String path, boolean timeStamps, boolean clearDoc) {
		this(new File(path), timeStamps, clearDoc);
	}

	/**
	 * closes the stream to the file.
	 */
	public void close() {
		try {
			mFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inits the File logger class by creating the neccassary stream writers.
	 * Catches only because this is a private method being called inside the
	 * constructors
	 * 
	 * @param file
	 * @param clearDoc
	 */
	private void init(@NonNull File file, boolean clearDoc) {
		mLoggerFile = file;
		if (file == null) {
			throw new NullPointerException("File is null");
		}
		if (!file.exists()) {
			try {
				if (!file.createNewFile()) {
					super.setError(true, "File provided did not exists and could not creeate new file");
				}
			} catch (IOException e) {
				super.setError(true, e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		try {
			mFileWriter = new FileWriter(mLoggerFile, clearDoc);
			mBufferedWriter = new BufferedWriter(mFileWriter);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Logs time to the current line
	 */
	public void logTime() {
		this.logTime(Log.DEFAULT_DATE_TIME_FORMAT);
	}

	/**
	 * @param format
	 */
	public void logTime(DateTimeFormatter format) {
		String time =  Log.getFormatedDateTime();
		
		this.logToCurrentLine(time);
	}

	/**
	 * writes string to file
	 * 
	 * @param str
	 */
	@Override
	public void log(String str) {
		this.log(new Log<>(str, this.mTimeStamps));
	}

	
	/**
	 *  writes string to file. The create new line.
	 *  Detects "\n" in the string which then creating a new line.
	 * 
	 * @param str String to be logged
	 */
	@Override
	public void log(String log, boolean logTime) {
		Util.checkNullArgumentAndThrow(log);
		if (logTime)
			this.logTime();
		while (log.contains("\n")) {
			int index = log.indexOf("\n");
			if (index != -1) {
				String x = log.substring(0, log.indexOf("\n"));
				this.write(x);
				this.newLine();
				
				if (log.length() > 2)
					if (index + 2 >= log.length()) {
						this.newLine();
						break;
					}
					else
						log = log.substring(index + 2);
			}
		}
		if (!log.endsWith("\n")) {
			this.write(log);
			this.newLine();
		}
	}
	
	@Override
	public void log(Log<?> log) {
		Util.checkNullArgumentAndThrow(log);
		LogRecorder recorder = super.getLogRecorder();
		if (recorder != null) {
			recorder.recordLog(log);
		}
		this.logString(log.getFormattedLog());
	}
	

	@Override
	public void logAll(Log<?>[] logs) {
		Util.checkNullArgumentAndThrow(logs);
		// "Log array provided is null"
		
		for (Log<?> log : logs) {
			this.log(log);
		}
		
	}

	private void logString(String str) {
		this.log(str,false);
	}
	/**
	 * Writes string to same line
	 * 
	 * @param str String to be logged
	 */
	public void logToCurrentLine(String str) {
		this.write(str);
	}

	/**
	 * Logs multiple lines. Each line is a new line
	 * 
	 * @param lines An array of logs formated in lines
	 */
	
	public void logLines(String[] lines) {
		this.logLines(lines,this.mTimeStamps);
	}
	
	public void logLines(@NonNull String[] lines, boolean timeStamps) {
		Util.checkNullArgumentAndThrow(lines);
		if (lines != null)
			for (String line : lines)
				this.log(line, timeStamps);
	}

	/**
	 * Jumps to a new line.
	 */
	public void newLine() {
		this.newLines(1);
	}

	/**
	 * Logs empty lines to create line breaks and/or spaces
	 * 
	 * @param num Number of lines to create.
	 */
	public void newLines(int num) {
		try {
			for (int i = 0; i < num; i++) {
				mBufferedWriter.newLine();
				mBufferedWriter.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Private
	 * Writes content directly to the file using a buffered writter stream connected to the abstract file object.
	 * Handles IO exceptions.
	 * @param content Content to be written to the file.
	 */
	private void write(String content) {
		if (this.mBufferedWriter != null) {
			try {
				this.mBufferedWriter.write(content);
				this.mBufferedWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	
	public File getFile() {
		return this.mLoggerFile;
	}
	/**
	 * 
	 * @return Format object
	 */
	public DateTimeFormatter getTimeFormat() {
		return mTimeFormat;
	}

	/**
	 * 
	 * @param mTimeFormat Format object
	 */
	public void setTimeFormat(DateTimeFormatter mTimeFormat) {
		this.mTimeFormat = mTimeFormat;
	}
	
	/**
	 * 
	 * @return String representation of Object
	 */
	public String toString() {
		return super.toString();
	}

}
