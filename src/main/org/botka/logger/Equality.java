/**
 * File Name: Equality.java
 * Programmer: Jake Botka
 * Date Created: Dec 7, 2020
 *
 */
package main.org.botka.logger;

/**
 * @author Jake Botka
 *
 */
public interface Equality<T> {

	public boolean isEqual(T obj1, T obj2);
}
