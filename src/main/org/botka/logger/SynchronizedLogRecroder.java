/**
 * SynchronizedLogRecroder.java
 * Programmer: Jake Botka
 * Dec 3, 2020
 *
 */
package main.org.botka.logger;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.eclipse.jdt.annotation.NonNull;

import main.org.botka.logger.log.Log;

/**
 * Interface for Syncronized log recording.
 * 
 * @author Jake Botka
 *
 */
public interface SynchronizedLogRecroder extends LogRecorder {
	final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();

	public default ReentrantReadWriteLock getLockObject() {
		return LOCK;
	}

	public default boolean blockingRecordLog(@NonNull Log log) {
		WriteLock lock = null;
		try {
			lock = LOCK.writeLock();
			lock.lock();
			return this.recordLog(log);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (lock != null) {
				if (lock.isHeldByCurrentThread()) {
					lock.unlock();
				}
			}
		}
		
	}
	
	public default boolean blockingRecordLogs(@NonNull Log[] logs) {
		Lock lock = null;
		try {
			lock = LOCK.writeLock();
			return this.recordLogs(logs);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (lock != null) {
				lock.unlock();
			}
		}
	}
	
	public default boolean nonblockingRecordLog(@NonNull Log log) {
		if (!LOCK.isWriteLocked()) {
			Lock lock = null;
			try {
				lock = LOCK.writeLock();
				lock.lock();
				return this.recordLog(log);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (lock != null) {
					lock.unlock();
				}
			}
		}
		return false;
	}


	
	default boolean nonblockingRecordLogs(@NonNull Log[] logs) {
		if (!LOCK.isWriteLocked()) {
			Lock lock = null;
			try {
				lock = LOCK.writeLock();
				return this.recordLogs(logs);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (lock != null) {
					lock.unlock();
				}
			}
		}
		return false;
	}
	
	/**
	default List<Log> getSyncrhonizedLogs(){
		Lock lock = null;
		try {
			lock = LOCK.readLock();
			return this.getLogsAsList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (lock != null) {
				lock.unlock();
			}
		} 
		return null;
	}
	**/

	
	
	

}
