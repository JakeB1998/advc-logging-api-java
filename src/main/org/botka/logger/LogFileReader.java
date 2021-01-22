/**
 * File Name: LogFileReader.java
 * Programmer: Jake Botka
 * Date Created: Jan 21, 2021
 *
 */
package main.org.botka.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import main.org.botka.logger.log.Log;
import main.org.botka.logger.log.LogBody;
import main.org.botka.logger.log.LogContext;
import main.org.botka.logger.log.LogFactory;
import main.org.botka.logger.log.LogHeader;
import main.org.botka.utility.api.exceptions.IllegalNullArguementException;
import main.org.botka.utility.api.util.Util;

/**
 * @author Jake Botka
 *
 */
public class LogFileReader {

	private File mFile;
	
	//TODO
	private static LogContext[] parseLogs(String logFileContents){
		if (logFileContents != null) {
			int startIndex = logFileContents.indexOf("[");
			int endIndex = startIndex != -1 ? logFileContents.indexOf("[", startIndex + 1) : -1;
			LogContext[] parsedLogs = new LogContext[0];
			//Main function
			while(startIndex >= 0) {
				
				String log = null;
				String header = null;
				String body = null;
				if (endIndex == -1) {
					log = logFileContents.substring(startIndex);
					logFileContents = "";
				} else {
					//System.err.println(logFileContents.length() + "," + endIndex);
					log = logFileContents.substring(startIndex, endIndex);
					logFileContents = logFileContents.substring(endIndex);
				}
				if (log != null) {
					//parse header and body from log.
					int parsedIndexStart = log.indexOf(LogHeader.HEADER_START_CHAR);
					int parsedIndexEnd = parsedIndexStart != -1 ? log.indexOf(LogHeader.HEADER_END_CHAR, parsedIndexStart + 1) : -1;
					if (parsedIndexStart != -1 && parsedIndexEnd != -1) {
						header = log.substring(parsedIndexStart,parsedIndexEnd);
						body = log.substring(parsedIndexEnd).trim();
						//System.out.println(header + "," + body);
					}
				}
				parsedLogs = Arrays.copyOf(parsedLogs, parsedLogs.length + 1);
				parsedLogs[parsedLogs.length - 1] = new LogContext(new Log(new LogHeader(String.valueOf(header)), new LogBody(String.valueOf(body))));
				startIndex = logFileContents.indexOf("[");
				endIndex = startIndex != -1 ? logFileContents.indexOf("[", startIndex + 1) : -1;
			}
			return parsedLogs;
		} else {
			if (Logger.isLoggingSystemDebugs()) {
				Logger.globalLogger().log(LogFactory.createErrorLog(true, LogFileReader.class, "File contents of log file is null."
					+ " Unable to parse log file contents to Redable Log Contexcts."));
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param logFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static List<LogContext> readLogs(@Nonnull File logFile) throws FileNotFoundException{
		return readLogs(logFile,  ArrayList.class);
	}
	
	/**
	 * 
	 * @param <T> Type param of a class that extends from list
	 * @param logFile
	 * @param implementingListClass
	 * @return
	 * @throws FileNotFoundException
	 */
	public static <T extends List<?>> List<LogContext>  readLogs(@Nonnull File logFile, @Nonnull Class<T> implementingListClass) throws FileNotFoundException{
		Util.checkNullArgumentAndThrow(logFile, IllegalNullArguementException.formatEceptionMessage("logFile"));
		Util.checkNullArgumentAndThrow(implementingListClass, IllegalNullArguementException.formatEceptionMessage("implementingListClass"));
		List<LogContext> logList = null;
		if (logFile.exists() && logFile.canRead()) {
			try {
				String fileContent = null;
				try {
					fileContent = Util.stringifyLines(Files.readAllLines(logFile.toPath()));
					if (fileContent != null) {
						logList = (List<LogContext>) implementingListClass.newInstance();
						if (logList != null) {
							logList.clear();
						}
						LogContext[] arr = parseLogs(fileContent);
						if (arr != null) {
							for (LogContext logContext : arr) {
								logList.add(logContext);
							}
							return logList;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			//check errors
			if (!logFile.exists()) {
				throw new FileNotFoundException();
			} else {
				if (!logFile.canRead()) {
					throw new SecurityException("File does not have the neccassary permisons to read from. ACCESS DENIED");
				}
			}
			
		}
		return null;
	}
	
	/**
	 * Default constructor.
	 */
	public LogFileReader() {
		mFile = null;
	}

}
