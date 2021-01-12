/**
 * File Name: FileLoggerTests.java
 * Programmer: Jake Botka
 * Date Created: Jan 6, 2021
 *
 */
package test.org.botka.logger;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.org.botka.logger.BaseLogger;
import main.org.botka.logger.FileLogger;
import main.org.botka.logger.LogHistoryRecorder;
import main.org.botka.logger.Logger;
import main.org.botka.logger.log.Log;
import main.org.botka.utility.api.util.FileUtil;


/**
 * @author Jake Botka
 *
 */
public class FileLoggerTests {
	File mFile = new File("LoggerTestFile.txt");
	FileLogger mFileLogger;
	private Logger mLogger;
	private Logger mDefaultLogger;
	
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
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		this.mLogger = new BaseLogger(LogHistoryRecorder.getDefaultLogRecorder());
		this.mDefaultLogger = new BaseLogger();
		File file = FileUtil.findOrCreate("testlogger.LOG");
		this.mFileLogger = new FileLogger(file , true,false);
		
	}

	/**
	 * @throws java.lang.Exception
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		this.mLogger = null;
		this.mDefaultLogger = null;
		
	}

	@Test
	public void defaultLogTest() {
		assertNotNull(this.mDefaultLogger.getLogRecorder());
	}
	
	
	@Test
	public void fileLoggerTest() {
		this.mFileLogger.log("Hello this is the first log");
		this.mFileLogger.log(new Log<String>("This is the second log", System.currentTimeMillis()));
		this.mFileLogger.log(new Log<String>("This is the long log....I am trying to close my laptop lid without anything happening to my 2nd monitor."
				+ "Lets say I've got streaming video up on my second monitor, all nice and full screen- and I have email and other boring stuff on my primary, laptop screen. "
				+ "I'm done, want to kick back, and enjoy the end of a movie, so I close the lid of my laptop- now everything moves onto the 2nd external monitor as if it was the single primary monitor."
				+ " I just want it to stay the same. As if I had only turned one monitor off.", System.currentTimeMillis()));
		FileLogger logger = (FileLogger)this.mFileLogger;
		
	}
	
	

	/**
	 * Test method for {@link main.org.botka.logger.FileLogger#log(main.org.botka.logger.log.Log)}.
	 */
	@Test
	public void testLogLogOfQ() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.FileLogger#logAll(main.org.botka.logger.log.Log<?>[])}.
	 */
	@Test
	public void testLogAllLogOfQArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.FileLogger#log(java.lang.String)}.
	 */
	@Test
	public void testLogString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.FileLogger#logLines(java.lang.String[])}.
	 */
	@Test
	public void testLogLinesStringArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.org.botka.logger.FileLogger#logLines(java.lang.String[], boolean)}.
	 */
	@Test
	public void testLogLinesStringArrayBoolean() {
		fail("Not yet implemented");
	}

}
