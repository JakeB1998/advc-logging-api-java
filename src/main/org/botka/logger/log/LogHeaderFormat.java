/**
 * File Name: LogHeaderFormat.java
 * Programmer: Jake Botka
 * Date Created: Jan 13, 2021
 *
 */
package main.org.botka.logger.log;

import java.util.Arrays;
import java.util.Vector;

/**
 * Formatter for the header of logs.
 * Should be used with the LogHeader class.
 * @author Jake Botka
 *
 */
public class LogHeaderFormat {

	
	public static final short TIME_ID = 0;
	public static final short CLASS_ID = 1;
	public static final short LOG_TYPE_ID = 2;
	public static final short LOG_TAG_ID = 3;
	public static final short[] DEFAULT_HEADER_ORDERING = {TIME_ID, CLASS_ID, LOG_TYPE_ID, LOG_TAG_ID};
	public static final LogHeaderFormat DEFAULT_FROMAT = new LogHeaderFormat(false, DEFAULT_HEADER_ORDERING);
	
	
	private short[] mHeaderOrderIds;
	private boolean mLogNullHeaderItems;
	
	
	
	
	public LogHeaderFormat(boolean logNulls) {
		mHeaderOrderIds = new short[0];
		mLogNullHeaderItems = logNulls;
	}
	
	public LogHeaderFormat(boolean logNulls, short[] HeaderItemOrdering) {
		this(logNulls);
		mHeaderOrderIds = HeaderItemOrdering;
	}
	
	public boolean isLoggingNulls() {
		return mLogNullHeaderItems;
	}
	
	public void setLogNullItems(boolean value) {
		mLogNullHeaderItems = value;
	}
	
	/**
	 * Gets the ordering of item headers by its id's. Pass this to the LogHeader object so that it can re order how header items are logged.
	 * @return array of id's in order in which header items are to be logged.
	 * @see LogHeader
	 */
	public short[] getHeaderOrdering() {
		return mHeaderOrderIds;
	}
	
	/**
	 * Sets the ordering format of header items in which the oder of each item to be looged in a logs header.
	 * @param orderIds
	 * @see LogHeader
	 */
	public void setHeaderOrdering(short[] orderIds) {
		mHeaderOrderIds = orderIds;
	}
	
	public static class ItemOrderBuilder {
		private short[] mIdOrder;
		public ItemOrderBuilder() {
			mIdOrder = new short[0];
		}
		
		private void addItem(short id) {
			mIdOrder = Arrays.copyOf(mIdOrder, mIdOrder.length + 1);
			mIdOrder[mIdOrder.length - 1] = id;
		}
		
		public ItemOrderBuilder clearOrdering() {
			mIdOrder = new short[0];
			return this;
		}
		
		public ItemOrderBuilder timeStamp() {
			addItem(TIME_ID);
			return this;
		}
		
		public ItemOrderBuilder classSource() {
			addItem(CLASS_ID);
			return this;
		}
	
		public ItemOrderBuilder logType() {
			addItem(LOG_TYPE_ID);
			return this;
		}
		
		public ItemOrderBuilder logTag() {
			addItem(LOG_TAG_ID);
			return this;
		}
		
		public short[] buildOrderArray() {
			return mIdOrder;
		}
	}

}
