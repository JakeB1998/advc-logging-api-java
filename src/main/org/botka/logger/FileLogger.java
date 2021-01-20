package main.org.botka.logger;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jdt.annotation.NonNull;




import main.org.botka.logger.log.Log;
import main.org.botka.logger.log.LogFactory;
import main.org.botka.logger.log.LogTime;
import main.org.botka.utility.api.base.FileWriteMode;
import main.org.botka.utility.api.io.readers.FileReader;
import main.org.botka.utility.api.util.Util;


public class FileLogger extends BaseLogger {
	public static final FileWriteMode FILE_WRITE_MODE_DEFAULT = FileWriteMode.Append;
	public static final boolean TIME_STAMP_PERM_DEFAULT = false;
	public static final String FILE_LOG_START_TEXT = " ***START LOG STREAM*** ";
	public static final String FILE_LOG_END_TEXT = " ***END LOG STREAM*** ";
	public static final String FILE_HEADER_TEXT = "This is a Log file Header.";
	public static final String FILE_FOOTER_TEXT = "";
	
	private Logger mSystemLogger;
	private FileWriteMode mFileWriteMode;
	private File mLoggerFile, mTempDirectory;
	private BufferedWriter mBufferedWriter;
	private FileWriter mFileWriter;
	private FilterReader mFileReader;
	
	private int mCurrentLine;
	private boolean mTimeStamps, mIncludeDefHeader, mIncludeDefFooter;

	/**
	 * Constructor.
	 */
	public FileLogger() {
		super();
		mSystemLogger = Logger.globalLogger();
		this.mLoggerFile = null;
		this.mBufferedWriter = null;
		this.mFileWriter = null;
		this.mFileReader = null;
		mIncludeDefHeader = true;
		mIncludeDefFooter = true;
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
		if (file == null) {
			throw new IllegalStateException("File is null");
		}
		if (mTempDirectory == null) {
			try {
				mTempDirectory = Files.createTempDirectory("temp").toFile();
			} catch (IOException e) {
				setError(true,  e.getMessage());
				e.printStackTrace();
			}
		}
		mLoggerFile = file;
		
		
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
		
		if (!hasError()) {
			//create temp file
		} else {
			Logger.Console.logError(true, getClass(), "There is a error present on file logger during file initialization: '" + getErrorMessage() + "'");
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
	 * Private
	 * Writes content directly to the file using a buffered writter stream connected to the abstract file object.
	 * Handles IO exceptions.
	 * @param content Content to be written to the file.
	 */
	private void write(String content) {
		File tempLoggerFile = null;
		try {
			tempLoggerFile = File.createTempFile("tempfiles", ".txt", mTempDirectory);
			
		} catch (IOException e2) {
			
			e2.printStackTrace();
		}
		File writingFile = tempLoggerFile;
		boolean writePermissions = false;
		//Error check one. Checks files context.
		if (!mLoggerFile.exists()) {
			try {
				if (!mLoggerFile.createNewFile()) {
					setError(true, "Could not create a new file due to the abcesne of provided file. Please provide new File");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (mBufferedWriter == null) {
			try {
				
				List<String> tempLines = Files.readAllLines(mLoggerFile.toPath());
				String text = "";
				final StringBuilder strBuilder = new StringBuilder(text);
				if (tempLines != null) {
					for (String line : tempLines) {
						strBuilder.append("\n" + line);
					}
					text = strBuilder.toString();
				}
				
				int indexStart = text.indexOf(FILE_LOG_START_TEXT);
				int indexEnd = text.indexOf(FILE_LOG_END_TEXT);
				if (indexStart >= 0 && indexEnd < text.length() && indexStart <= indexEnd) {
					text = text.substring(indexStart + FILE_LOG_START_TEXT.length(),indexEnd).trim();
					
				} else {
					text = "";
				}
				System.err.println( text);
				mFileWriter = new FileWriter(tempLoggerFile, true);
				mBufferedWriter = new BufferedWriter(mFileWriter);
				if (mIncludeDefHeader) {
					mBufferedWriter.write(FILE_HEADER_TEXT);
					mBufferedWriter.write( "\n" + FILE_LOG_START_TEXT + "\n");
					mBufferedWriter.write(text);
					mBufferedWriter.flush();
				}
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		}
		//Error check Two. Checks permissions.
		if (!hasError()) {
			if (writingFile.canWrite()) {
				writePermissions = true;
				//Main function.
				if (writePermissions == true) {
					if (this.mBufferedWriter != null) {
						try {
							this.mBufferedWriter.write(content);
							this.mBufferedWriter.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							try {
								if (mIncludeDefFooter) {
									mBufferedWriter.write("\n" + FILE_LOG_END_TEXT);
									mBufferedWriter.write( "\n" + FILE_FOOTER_TEXT);
								}
								mBufferedWriter.close();
								mBufferedWriter = null;
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
							if (!writingFile.getAbsolutePath().equals(mLoggerFile.getAbsolutePath())) {
								//writingFile.renameTo(mLoggerFile);
								try {
									Files.move(writingFile.toPath(), mLoggerFile.toPath(),  StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
								} catch (IOException e) {
									//Logger.globalLogger().log(LogFactory.createErrorLog(getClass(), e.getMessage()));
									e.printStackTrace();
								}
								
							}
						} 	
					}
				}
			} else {
				Logger.globalLogger().log(LogFactory.createErrorLog(getClass(), "File does not have write permissions"));
			}
		} else {
			Logger.globalLogger().log(LogFactory.createErrorLog(getClass(),"File was not found and could not be created"));
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
