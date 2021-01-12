/**
 * File Name: LogHistoryRecorderTests.java
 * Programmer: Jake Botka
 * Date Created: Jan 12, 2021
 *
 */
package test.org.botka.logger;

import static org.junit.Assert.*;

import java.util.Arrays;

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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#LogHistoryRecorder()}.
	 */
	@Test
	public void testLogHistoryRecorder() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#LogHistoryRecorder(main.org.botka.logger.log.Log<?>[])}.
	 */
	@Test
	public void tesLogArray() {
		Log[] logs =  LogSampleData.STRING_LOG_SMALL_SAMPLE;
		Logger.Console.log(true, getClass(), Arrays.toString(logs));
	}

	

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#clearLogs()}.
	 */
	@Test
	public void testClearLogs() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#recordLog(main.org.botka.logger.log.Log)}.
	 */
	@Test
	public void testRecordLog() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#recordLogs(main.org.botka.logger.log.Log<?>[])}.
	 */
	@Test
	public void testRecordLogs() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#getLogs()}.
	 */
	@Test
	public void testGetLogs() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#getLogsAsList()}.
	 */
	@Test
	public void testGetLogsAsList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogHistoryRecorder#getLogCollectionCopy()}.
	 */
	@Test
	public void testGetLogCollectionCopy() {
		fail("Not yet implemented");
	}

}
