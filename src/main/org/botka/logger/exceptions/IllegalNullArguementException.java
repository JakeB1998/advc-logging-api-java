/**
 * IllegalNullArguementException.java
 * Programmer: Jake Botka
 * Nov 23, 2020
 *
 */
package main.org.botka.logger.exceptions;

import org.eclipse.jdt.annotation.NonNull;

/**
 * @author Jake Botka
 *
 */
public class IllegalNullArguementException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3488617396374469003L;

	/**
	 *
	 */
	public IllegalNullArguementException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *@param s
	 */
	public IllegalNullArguementException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 *@param cause
	 */
	public IllegalNullArguementException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 *@param message
	 *@param cause
	 */
	public IllegalNullArguementException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	public static String formatEceptionMessage(@NonNull String variableName) {
		return "Arguement: " + "'" + variableName + "'" + "  is null when @NonNull is attached. This arguement can not be null.";
	}

}
