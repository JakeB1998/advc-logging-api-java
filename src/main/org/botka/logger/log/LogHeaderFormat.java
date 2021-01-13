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
 * @author Jake Botka
 *
 */
public class LogHeaderFormat {

	public static final short TIME_ID = 0;
	public static final short CLASS_ID = 1;
	public static final short LOG_TYPE_ID = 2;
	public static final short LOG_TAG_ID = 3;
	public static final short[] DEFAULT_HEADER_ORDERING = {TIME_ID, CLASS_ID, LOG_TYPE_ID, LOG_TAG_ID};
	
	
	/**
	 * 
	 */
	public LogHeaderFormat() {
		// TODO Auto-generated constructor stub
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
