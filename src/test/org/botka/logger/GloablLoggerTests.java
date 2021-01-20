/**
 * File Name: GloablLoggerTests.java
 * Programmer: Jake Botka
 * Date Created: Jan 20, 2021
 *
 */
package test.org.botka.logger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import main.org.botka.logger.Logger;
import main.org.botka.logger.log.LogFactory;

/**
 * @author Jake Botka
 *
 */
public class GloablLoggerTests {

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
		Logger.globalLogger().log(LogFactory.createErrorLog(getClass(), "This is a test error"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Logger globalLogger = Logger.globalLogger();
		assertTrue(globalLogger != null);
		
	}

}
