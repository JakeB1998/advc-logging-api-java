/**
 * File Name: ErrorWrapper.java
 * Programmer: Jake Botka
 * Date Created: Jan 13, 2021
 *
 */
package main.org.botka.logger;

/**
 * @author Jake Botka
 *
 */
public class ErrorWrapper {

	private final Exception EXCEPTION;
	private final String EXCEPTION_MESSAGE;
	
	/**
	 * 
	 * @param exception Error exception
	 */
	public ErrorWrapper(Exception exception) {
		EXCEPTION = exception;
		EXCEPTION_MESSAGE = exception != null ? exception.getMessage() : null;
	}

	/**
	 * 
	 * @return
	 */
	public Exception getErrorException() {
		return EXCEPTION;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		return EXCEPTION_MESSAGE;
	}

	/**
	 * @return String representation of this object instance.
	 */
	@Override
	public String toString() {
		return "ErrorWrapper [EXCEPTION=" + EXCEPTION + ", EXCEPTION_MESSAGE=" + EXCEPTION_MESSAGE + "]";
	}
}
