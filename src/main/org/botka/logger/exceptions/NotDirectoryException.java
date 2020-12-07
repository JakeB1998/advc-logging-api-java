/**
 * NotDirectoryException.java
 * Programmer: Jake Botka
 * Dec 2, 2020
 *
 */
package main.org.botka.logger.exceptions;

/**
 * @author Jake Botka
 *
 */
public class NotDirectoryException extends Exception {

	
	private static final long serialVersionUID = -5975075070819237286L;
	public static final String DEFAULT_ERROR_MESSAGE = "Path is not a directory";

	/**
	 * 
	 */
	public NotDirectoryException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public NotDirectoryException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NotDirectoryException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * @param arg0
	 */
	public NotDirectoryException(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	public NotDirectoryException(Throwable arg0) {
		super(arg0);
		
	}
	
	public static String formatErrorMessage(String path) {
		return "This path'" + path + "' is not a directory";
	}

}
