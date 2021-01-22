/**
 * File Name: Util.java
 * Programmer: Jake Botka
 * Date Created: Jan 13, 2021
 *
 */
package main.org.botka.logger;

import java.util.List;

/**
 * @author Jake Botka
 *
 */
public final class Util {

	/**
	 * 
	 */
	private Util() {
		// TODO Auto-generated constructor stub
	}
	
	public static String stringifyLines(String[] lines) {
		if (lines != null) {
			final StringBuilder strBuilder = new StringBuilder("");
			for (String line : lines) {
				//Use value off to stringify nulls
				strBuilder.append(String.valueOf(line));
			}
			return strBuilder.toString();
		}
		return null;
	}
	
	public static String stringifyLines(List<String> lines) {
		return lines != null ? stringifyLines(lines.toArray(new String[0])) : null;
	}

}
