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

import main.org.botka.logger.log.Log;
import main.org.botka.logger.log.LogTime;
import main.org.botka.utility.api.base.FileWriteMode;
import main.org.botka.utility.api.util.Util;


public class FileLogger extends BaseLogger {
	public static final FileWriteMode FILE_WRITE_MODE_DEFAULT = FileWriteMode.Append;
	public static final boolean TIME_STAMP_PERM_DEFAULT = false;
	private FileWriteMode mFileWriteMode;
	private File mLoggerFile;
	private BufferedWriter mBufferedWriter;
	private FileWriter mFileWriter;
	private FilterReader mFileReader;
	
	private int mCurrentLine;
	private boolean mTimeStamps;

	/**
	 * Constructor.
	 */
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
	 * 
	 * @param file
	 * @param fileWriteMode
	 */
	public FileLogger(File file, FileWriteMode fileWriteMode) {
		this(file, fileWriteMode,TIME_STAMP_PERM_DEFAULT);
	}
	
	/**
	 * 
	 * @param file
	 * @param fileWriteMode
	 * @param timeStamps
	 */
	public FileLogger(File file, FileWriteMode fileWriteMode, boolean timeStamps) {
		this(file, timeStamps, FILE_WRITE_MODE_DEFAULT.toString().equals(FileWriteMode.Overwrite.toString()));
	}

	/**
	 * Constructor
	 * 
	 * @param file       File to be logged to
	 * @param timeStamps True if Time should be logged
	 */
	public FileLogger(File file, boolean timeStamps) {
		this(file,timeStamps, FILE_WRITE_MODE_DEFAULT.toString().equals(FileWriteMode.Overwrite.toString()));
	}

	/**
	 * Main constructor.
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

	/**
	 * 
	 * @param filePath
	 */
	public FileLogger(String path) {
		this(path, TIME_STAMP_PERM_DEFAULT);
	}
	
	/**
	 * 
	 * @param path path to the file.
	 * @param timeStamps
	 */
	public FileLogger(String path, boolean timeStamps) {
		this(path, TIME_STAMP_PERM_DEFAULT, FILE_WRITE_MODE_DEFAULT.toString().equals(FileWriteMode.Overwrite.toString()));
		this.mTimeStamps = timeStamps;
	}

	/**
	 * 
	 * @param path File path.
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
	 * writes string to file
	 * 
	 * @param str
	 */
	@Override
	public void log(String str) {
		this.log(new Log(str, System.currentTimeMillis()));
	}

	
	/**
	 * 
	 * @param log
	 *
	 */
	@Override
	public void log(Log log) {
		LogRecorder recorder = super.getLogRecorder();
		if (recorder != null) {
			recorder.recordLog(log);
		}
		write(log.getFormattedLog());
	}
	
	/**
	 * 
	 * @param logs
	 *
	 */
	@Override
	public void logAll(Log[] logs) {
		if (logs != null) {
			for (Log log : logs) {
				log(log);
			}
		}
		
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
	
	/**
	 * 
	 * @param lines
	 * @param timeStamps
	 */
	public void logLines( String[] lines, boolean timeStamps) {
		String header = "";
		if (timeStamps) {
			header = "[" + LogTime.getFormatedDateTime() + "] ";
		}
		
		if (lines != null) {
			for (String line : lines) {
				if (line == null) {
					line = "Null";
				}
				write(header + line);
			}
		}
				
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
		
		boolean errorFlag = false;
		boolean writePermissions = false;
		//Error check one. Checks files context.
		if (!mLoggerFile.exists()) {
			try {
				if (!mLoggerFile.createNewFile()) {
					errorFlag = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
				errorFlag = true;
			}
		}
		//Error check Two. Checks permissions.
		if (!errorFlag) {
			if (mLoggerFile.canWrite()) {
				writePermissions = true;
			} else {
				System.err.println("File does not have write permissions");
			}
		} else {
			System.err.println("File was not found and could not be created");
		}
		//Main function.
		if (errorFlag == false && writePermissions == true) {
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
	}


	/**
	 * Get the file that is being logged to.
	 * @return file that the current logger is logging to.
	 */
	public File getFile() {
		return this.mLoggerFile;
	}
	
	
	
	/**
	 * 
	 * @return String representation of Object
	 */
	public String toString() {
		return super.toString();
	}

}
