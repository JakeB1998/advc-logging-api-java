/**
 * File Name: LogFileReaderTests.java
 * Programmer: Jake Botka
 * Date Created: Jan 21, 2021
 *
 */
package test.org.botka.logger;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.org.botka.logger.LogFileReader;
import main.org.botka.logger.Logger;
import main.org.botka.logger.log.LogContext;

/**
 * @author Jake Botka
 *
 */
public class LogFileReaderTests {

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogFileReader#readLogs(java.io.File)}.
	 */
	@Test
	public void testReadLogsFile() {
		try {
			List<LogContext> logs = LogFileReader.readLogs(LogSampleData.LOG_TEST_FILE);
			assertNotNull(logs);
			Logger.Console.log(true, getClass(), Arrays.toString(logs.toArray()));
			for (LogContext logContext : logs) {
				Logger.Console.log(true, getClass(), logContext.getLog().getFormattedBody());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		assertTrue(true);
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogFileReader#readLogs(java.io.File, java.lang.Class)}.
	 */
	@Test
	public void testReadLogsFileClassOfT() {
		try {
			ArrayList<LogContext> logs = (ArrayList<LogContext>) LogFileReader.readLogs(LogSampleData.LOG_TEST_FILE, ArrayList.class);
			assertNotNull(logs);
			assertTrue(logs.size() >= 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(true);
		
	}

}
