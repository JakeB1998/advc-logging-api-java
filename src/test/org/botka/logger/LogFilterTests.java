/**
 * File Name: LogFilterTests.java
 * Programmer: Jake Botka
 * Date Created: Feb 2, 2021
 *
 */
package test.org.botka.logger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.org.botka.logger.LogFilter;
import main.org.botka.logger.Logger;
import main.org.botka.logger.LogFilter.FilterOption;
import main.org.botka.logger.log.Log;
import main.org.botka.logger.log.LogBody;
import main.org.botka.logger.log.LogFactory;
import main.org.botka.logger.log.LogHeader;
import main.org.botka.logger.log.LogTag;


/**
 * @author Jake Botka
 *
 */
public class LogFilterTests {

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
	 * Test method for {@link main.org.botka.logger.LogFilter#filter(main.org.botka.logger.log.Log)}.
	 */
	@Test
	public void testFilter() {
		LogFilter filter = new LogFilter(FilterOption.LogTag, "ALL");
		assertNotNull(filter);
		Log[] logs = new Log[10];
		assertNotNull(logs);
		for (int i =0 ; i < logs.length; i++) {
			LogHeader logHeader = new LogHeader(System.currentTimeMillis(), getClass(), "ALL", null);
			logs[i] = new Log(logHeader, new LogBody(logHeader, "Log #" + String.valueOf(i)));
			if (i % 2 == 0) {
				logs[i].getLogHeader().setLogTag(new LogTag("Error"));
				assertNull(filter.filter(logs[i]));
			} else {
				assertNotNull(filter.filter(logs[i]));
			}
		}
		
		assertTrue(true);
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogFilter#filterAll(main.org.botka.logger.log.Log[])}.
	 */
	@Test
	public void testFilterAllLogArray() {
		LogFilter filter = new LogFilter(FilterOption.LogTag, "ALL");
		assertNotNull(filter);
		Log[] logs = new Log[10];
		assertNotNull(logs);
		for (int i =0 ; i < logs.length; i++) {
			LogHeader logHeader = new LogHeader(System.currentTimeMillis(), getClass(), "ALL", null);
			logs[i] = new Log(logHeader, new LogBody(logHeader, "Log #" + String.valueOf(i)));
			if (i % 2 == 0) {
				logs[i].getLogHeader().setLogTag(new LogTag("Error"));
			} 
		}
		Log[] filteredLogs = filter.filterAll(logs);
		assertNotNull(filteredLogs);
		assertTrue(filteredLogs.length == 5);
		for (Log Log : filteredLogs) {
			System.out.println(Log.getLogBody().getBodyContent());
		}
	}

	/**
	 * Test method for {@link main.org.botka.logger.LogFilter#filterAll(java.util.List)}.
	 */
	@Test
	public void testFilterAllListOfLog() {
		fail("Not yet implemented");
	}

}
