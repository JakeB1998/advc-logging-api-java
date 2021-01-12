/**
 * File Name: ConsoleLoggerTests.java
 * Programmer: Jake Botka
 * Date Created: Jan 12, 2021
 *
 */
package test.org.botka.logger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.org.botka.logger.Logger;

/**
 * @author Jake Botka
 *
 */
public class ConsoleLoggerTests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Logger.Console.log(true, ConsoleLoggerTests.class, "Tests are being set up");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Logger.Console.log(true, ConsoleLoggerTests.class, "Tests are being tore down");
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
	
	@Test
	public void testNullMessage() {
		Logger.Console.log(true, getClass(), null);
		assertTrue(true);
	}

	/**
	 * Test method for {@link main.org.botka.logger.ConsoleLogger#log(main.org.botka.logger.log.Log)}.
	 */
	@Test
	public void testLog() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.ConsoleLogger#logAll(main.org.botka.logger.log.Log<?>[])}.
	 */
	@Test
	public void testLogAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.ConsoleLogger#ConsoleLogger()}.
	 */
	@Test
	public void testConsoleLogger() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.ConsoleLogger#ConsoleLogger(boolean)}.
	 */
	@Test
	public void testConsoleLoggerBoolean() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.ConsoleLogger#logString(java.lang.String)}.
	 */
	@Test
	public void testLogStringString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.ConsoleLogger#logString(java.lang.String, boolean)}.
	 */
	@Test
	public void testLogStringStringBoolean() {
		fail("Not yet implemented");
	}

}
