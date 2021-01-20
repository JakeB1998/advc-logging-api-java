/**
 * File Name: LogHistoryRecorderTests.java
 * Programmer: Jake Botka
 * Date Created: Jan 12, 2021
 *
 */
package test.org.botka.logger;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.xml.transform.dom.DOMLocator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.org.botka.logger.LogHistoryRecorder;
import main.org.botka.logger.LogRecorder;
import main.org.botka.logger.Logger;
import main.org.botka.logger.log.Log;

/**
 * @author Jake Botka
 *
 */
public class LogHistoryRecorderTests {
	private static boolean logging = true;
	private LogRecorder mLogRecorder;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		if (mLogRecorder == null) {
			mLogRecorder = LogHistoryRecorder.getDefaultLogRecorder();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		if (mLogRecorder != null) {
			mLogRecorder.clearLogs();
		}
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#getDefaultLogRecorder()}.
	 */
	@Test
	public void testGetDefaultLogRecorder() {
		assertTrue(LogHistoryRecorder.getDefaultLogRecorder() != null);
	}

	

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#LogHistoryRecorder(main.org.botka.logger.log.Log<?>[])}.
	 */
	@Test
	public void testLogArray() {
		Log[] logs =  LogSampleData.STRING_LOG_SMALL_SAMPLE;
		if (logging) {
			Logger.Console.log(true, getClass(), Arrays.toString(logs));
		}
	}

	

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#clearLogs()}.
	 */
	@Test
	public void testClearLogs() {
		Log[] logs =  LogSampleData.STRING_LOG_SMALL_SAMPLE;
		mLogRecorder.recordLogs(logs);
		assertTrue(logs != null && mLogRecorder != null);
		assertTrue(mLogRecorder.getLogCount() == logs.length);
		mLogRecorder.clearLogs();
		assertTrue(mLogRecorder.getLogCount() == 0);
		
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#recordLog(main.org.botka.logger.log.Log)}.
	 */
	@Test
	public void testRecordLog() {
		Log[] logs = LogSampleData.STRING_LOG_SMALL_SAMPLE;
		assertTrue(mLogRecorder != null && logs != null);
		mLogRecorder.recordLog(logs[0]);
		assertTrue(mLogRecorder.getLogs()[0].equals(logs[0]));
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#recordLogs(main.org.botka.logger.log.Log<?>[])}.
	 */
	@Test
	public void testRecordLogs() {
		Log[] logs = LogSampleData.STRING_LOG_SMALL_SAMPLE;
		assertTrue(mLogRecorder != null && logs != null);
		mLogRecorder.recordLogs(logs);
		assertTrue(Arrays.equals(mLogRecorder.getLogs(), logs));
		
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#getLogs()}.
	 */
	@Test
	public void testGetLogs() {
		Log[] logs = LogSampleData.STRING_LOG_SMALL_SAMPLE;
		assertTrue(mLogRecorder != null && logs != null);
		assertTrue(mLogRecorder.getLogs().length == 0);
		mLogRecorder.recordLogs(logs);
		assertTrue(Arrays.equals(mLogRecorder.getLogs(), logs));
		
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#getLogsAsList()}.
	 */
	@Test
	public void testGetLogsAsList() {
		Log[] logs = LogSampleData.STRING_LOG_SMALL_SAMPLE;
		assertTrue(mLogRecorder != null && logs != null);
		mLogRecorder.recordLogs(logs);
		List<Log> logList = mLogRecorder.getLogsAsList();
		assertTrue(Arrays.equals(logList.toArray(new Log[0]), logs));
		
	}

	

}
