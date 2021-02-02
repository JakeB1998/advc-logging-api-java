/**
 * File Name: LogFilter.java
 * Programmer: Jake Botka
 * Date Created: Feb 2, 2021
 *
 */
package main.org.botka.logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import main.org.botka.logger.log.Log;
import main.org.botka.utility.api.base.Filter;

/**
 * @author Jake Botka
 *
 */
public class LogFilter implements Filter<Log> {

	public enum FilterOption {
		NA,LogTag, LogSource, LogType, Custom
	}
	private FilterOption mFilterOption;
	private String mFilterSelectionKey;
	private String mFilterSelectionValue;
	
	
	/**
	 * 
	 */
	public LogFilter() {
		mFilterOption = FilterOption.NA;
		mFilterSelectionKey = null;
		mFilterSelectionValue = null;
		
	}
	
	/**
	 * 
	 * @param filterOption
	 */
	public LogFilter(FilterOption filterOption) {
		
		mFilterOption = filterOption;
		mFilterSelectionKey = filterOption.toString();
	}
	
	/**
	 * 
	 * @param filterOption
	 * @param selectionValue
	 */
	public LogFilter(FilterOption filterOption, String selectionValue) {
		this(filterOption);
		mFilterSelectionValue = selectionValue;
	}
	
	/**
	 * 
	 * @param filterOption
	 * @param selectionKey
	 * @param selectionValue
	 */
	public LogFilter(FilterOption filterOption, String selectionKey, String selectionValue) {
		this(filterOption,selectionValue);
		mFilterSelectionKey = selectionKey;
		
	}
	
	/**
	 * 
	 * @param SelectionKey
	 * @param selectionValue
	 */
	public LogFilter(String SelectionKey, String selectionValue) {
		mFilterOption = checkOption(SelectionKey);
		mFilterSelectionKey = SelectionKey;
		mFilterSelectionValue = selectionValue;
	}
	
	/**
	 * 
	 * @param logFilter
	 */
	public LogFilter(LogFilter logFilter) {
		this();
		if (logFilter != null) {
			mFilterOption = logFilter.getFilterOption();
		}
	}
	
	/**
	 * 
	 * @param selectionKey
	 * @return
	 */
	private FilterOption checkOption(String selectionKey) {
		if (selectionKey != null) {
			if (selectionKey.equals(FilterOption.LogTag.toString())) {
				return FilterOption.LogTag;
			} else if (selectionKey.equals(FilterOption.LogSource.toString())) {
				return FilterOption.LogSource;
			} else if (selectionKey.equals(FilterOption.LogType.toString())) { 
				return FilterOption.LogType;
			} else {
				return FilterOption.Custom;
			}
		}
		return FilterOption.NA;
	}

	

	/**
	 * @param filterable
	 * @return
	 */
	@Override
	public Log filter(Log filterable) {
		String selectionKey = mFilterOption.toString();
		if (selectionKey != null && mFilterSelectionValue != null) {
			if (selectionKey.equals(FilterOption.LogTag.toString())) {
				if (filterable != null && filterable.getLogTag() != null) {
					if (Objects.equals(filterable.getLogTag().getLogTag(), mFilterSelectionValue)) {
						return filterable;
					}
				}
			} else if (selectionKey.equals(FilterOption.LogSource.toString())) {
				if (filterable != null && filterable.getLogHeader() != null && filterable.getLogHeader().getLogSource() != null) {
					if (Objects.equals(filterable.getLogHeader().getLogSource().getName(), mFilterSelectionValue)) {
						return filterable;
					}
				}
				
			} else if (selectionKey.equals(FilterOption.LogType.toString())) { 
				if (filterable != null && filterable.getLogHeader() != null && filterable.getLogHeader().getLogType() != null) {
					if (Objects.equals(filterable.getLogHeader().getLogType().getLogTypeString(), mFilterSelectionValue)) {
						return filterable;
					}
				}
			} else {
				//cuustom
				String log = filterable.getFormattedLog();
				if (log != null) {
					if (log.contains(mFilterSelectionValue)) {
						return filterable;
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param filterable
	 * @return
	 */
	@Override
	public Log[] filterAll(Log[] filterables) {
		if (filterables != null) {
			Log[] filteredLogs = new Log[0];
			for (int i =0; i < filterables.length; i++) {
				Log log = filter(filterables[i]);
				if (log != null) {
				filteredLogs = Arrays.copyOf(filteredLogs, filteredLogs.length + 1);
				filteredLogs[filteredLogs.length - 1] = log;
				}
			}
			return filteredLogs;
		}
		return null;
	}
	
	/**
	 * @param filterables
	 * @return
	 */
	@Override
	public List<Log> filterAll(List<Log> filterables) {
		if (filterables != null) {
			Iterator<Log> iterable =filterables.iterator();
			while(iterable.hasNext()) {
				Log log = filter(iterable.next());
				if (log == null) {
					iterable.remove();
				}
			}
			return filterables;
		}
		return null;
	}

	
	/**
	 * 
	 * @return
	 */
	public FilterOption getFilterOption() {
		return mFilterOption;
	}
	
	/**
	 * 
	 * @param filterOption
	 */
	public void setFilterOption(FilterOption filterOption) {
		mFilterOption = filterOption;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSelectionKey() {
		return mFilterSelectionKey;
	}
	
	/**
	 * 
	 * @param selectionKey
	 */
	public void setSelectionKey(String selectionKey) {
		mFilterSelectionKey = selectionKey;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSelectionValue() {
		return mFilterSelectionValue;
	}
	
	/**
	 * 
	 * @param selectionValue
	 */
	public void setSelectionValue(String selectionValue) {
		mFilterSelectionValue = selectionValue;
	}
	
	
	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "LogFilter [mFilterOption=" + mFilterOption + ", mFilterSelectionKey=" + mFilterSelectionKey
				+ ", mFilterSelectionValue=" + mFilterSelectionValue + "]";
	}


	/**
	 * 
	 * @author Jake Botka
	 *
	 */
	public static class Builder {
		private FilterOption mFilterOption;
		private String mFilterSelectionKey;
		private String mFilterSelectionValue;
		
		public Builder filterBy(FilterOption filterOption, String SelectionValue) {
			return filterBy(filterOption.toString(), SelectionValue);
		}
		
		public Builder filterBy(String selectionKey, String SelectionValue) {
			mFilterSelectionKey = selectionKey;
			mFilterSelectionValue = SelectionValue;
			return this;
		}
		
		public Builder filterByTag(String SelectionValue) {
			return filterBy(FilterOption.LogTag, SelectionValue);
		}
		
		public LogFilter build() {
			return new LogFilter(mFilterOption, mFilterSelectionKey, mFilterSelectionValue);
		}
	}


	

}
