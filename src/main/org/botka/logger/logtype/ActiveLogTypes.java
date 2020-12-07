/**
 * File Name: ActiveLogTypes.java
 * Programmer: Jake Botka
 * Date Created: Dec 7, 2020
 *
 */
package main.org.botka.logger.logtype;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.jdt.annotation.NonNull;


/**
 * @author Jake Botka
 *
 */
public class ActiveLogTypes {

	private Set<LogType> mActiveLogTypes;
	public ActiveLogTypes() {
		this.mActiveLogTypes = new HashSet<>(0);
	}
	
	public ActiveLogTypes(int initialSize) {
		this.mActiveLogTypes = new HashSet<>(initialSize);
	}
	
	public ActiveLogTypes(@NonNull Collection<LogType> logTypes) {
		this(0);
		if (logTypes != null) {
			Iterator<LogType> logIterator = logTypes.iterator();
			if (logIterator != null) {
				while (logIterator.hasNext()) {
					this.mActiveLogTypes.add(logIterator.next());
				}
			}
		}
		
	}
	
	public void addLogType(@Nonnull LogType logType) {
		this.mActiveLogTypes.add(logType);
	}
	
	public void clearSet() {
		this.mActiveLogTypes.clear();
	}
	
	public void removeLogType(@Nonnull LogType logType) {
		this.mActiveLogTypes.remove(logType);
	}
	
	/**
	 * Sets active log types. This is not the same as the active logtypes in the recorder
	 * @param logTypes Array of logtypes which values can not be null. However the array itself can be null.
	 */
	public void setActiveLogTypes(@Nonnull LogType[] logTypes) {
		if (logTypes != null) {
			this.mActiveLogTypes.clear();
			for (LogType logType : logTypes) {
				if (logType != null) {
					this.mActiveLogTypes.add(logType);
				}
			}
		}
	}
	
	public void setActiveLogTypes(@Nonnull Collection<LogType> logTypes) {
		if (logTypes != null) {
			this.setActiveLogTypes(logTypes.toArray(new LogType[0]));
		} 
	}
	
	
}
