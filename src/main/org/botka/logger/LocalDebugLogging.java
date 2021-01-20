/**
 * File Name: LocalDebugLogging.java
 * Programmer: Jake Botka
 * Date Created: Jan 14, 2021
 *
 */
package main.org.botka.logger;

/**
 * For local Debug logging.
 * @author Jake Botka
 *
 */
public interface LocalDebugLogging {
	
	public default void disbaleOverrideGlobalDebugging() {
		setGloablLoggingOverride(false);
	}

	public default void ovverideGlobalDebugging() {
		setGloablLoggingOverride(true);
	}
	
	public default void  overrideGlobalDebugging(boolean localDebugLogPermissions) {
		setGloablLoggingOverride(true);
		setLocalDebugLogging(localDebugLogPermissions);
	}
	
	/**
	 * Determines if you can debug log based on the local debug ovverides and the global debug loging variable in Logger.class.
	 * If global logging is overriden locally then will be based on @code{isDebugLoggingLocally()}. If not then will be based on the value @code{Logger.globalDebugLogging}.
	 * @return True if you can debug log, otherwise false.
	 * @see Logger.class
	 */
	public default boolean canDebugLog() {
		if (isGlobalDebugLoggingOverriden()) {
			return isDebugLoggingLocally();
		} else {
			return Logger.globalDebugLogging;
		}
		
	}
	
	public boolean isGlobalDebugLoggingOverriden();
	public boolean isDebugLoggingLocally();
	
	public void setGloablLoggingOverride(boolean override);
	public void setLocalDebugLogging(boolean localDebugLogging);
}
