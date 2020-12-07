/**
 * DeepCloneInstance.java
 * Programmer: Jake Botka
 * Nov 16, 2020
 *
 */
package main.org.botka.logger.serialization;

import main.org.botka.logger.Util;

/**
 * Interface that provides defauly implementation for classes to be deep coppied
 * intoa new instance with ZERO linking refrences.
 * 
 * @author Jake Botka
 *
 */
public interface DeepCloneInstance<T> {
	/**
	 * Calls method of same name from Util.java but handles Object casting and
	 * encapsulates in implementing class.
	 * 
	 * @param instanceToBeDeepCopied
	 * @return New deep copied instance.
	 */
	public default T deepCopyNewInstance(T instanceToBeDeepCopied) {
		return Util.deepCopyNewInstance(instanceToBeDeepCopied);
	}

}
