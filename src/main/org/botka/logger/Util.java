/**
 * Util.java
 * Programmer: Jake Botka
 * Nov 9, 2020
 *
 */
package main.org.botka.logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.eclipse.jdt.annotation.NonNull;

import main.org.botka.logger.exceptions.IllegalNullArguementException;
import main.org.botka.logger.exceptions.NotImplementedYetException;



/**
 * Class that will contain methods from all Utility classes in one centralized
 * class.
 * 
 * @author Jake Botka
 *
 */
public final class Util {

	private Util() {
		// can not see
	}

	/**
	 * Checks if object is null. If it is null it will throw a null pointer
	 * exception.
	 * 
	 * @param object Object to check.
	 *
	 */
	public static void checkNullAndThrow(Object object) {
		if (object == null) {
			throw new NullPointerException(object.toString() + "Was null");
		}
	}

	public static void checkNullArgumentAndThrow(Object arguement) {
		checkNullArgumentAndThrow(arguement, "Arguement can not be null.");
	}

	public static void checkNullArgumentAndThrow(Object arguement, String errorMessage) {
		if (isNull(arguement)) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T deepCopyNewInstance(T instanceToBeDeepCopied) {
		Object original = instanceToBeDeepCopied;
		byte[] arr = Serialization.serailizeObject(instanceToBeDeepCopied);
		if (arr != null && arr.length > 0) {
			try {

				return (T) Serialization.deserailizeObject(arr);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				arr = null;
			}
		}
		return null;
	}

	public static boolean notNull(Object obj) {
		return obj != null;
	}

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	/**
	 * Template for overloading methods
	 * 
	 * @author Jake Botka
	 *
	 */
	private static class Template {

		public void methodcall(byte variable) {
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Byte variable) {
			Util.checkNullArgumentAndThrow(variable, IllegalNullArguementException.formatEceptionMessage("variable"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull byte[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Byte[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(short variable) {
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Short variable) {
			Util.checkNullArgumentAndThrow(variable, IllegalNullArguementException.formatEceptionMessage("variable"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull short[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Short[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(int variable) {
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Integer variable) {
			Util.checkNullArgumentAndThrow(variable, IllegalNullArguementException.formatEceptionMessage("variable"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull int[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Integer[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(long variable) {
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Long variable) {
			Util.checkNullArgumentAndThrow(variable, IllegalNullArguementException.formatEceptionMessage("variable"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull long[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Long[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(double variable) {
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Double variable) {
			Util.checkNullArgumentAndThrow(variable, IllegalNullArguementException.formatEceptionMessage("variable"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull double[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Double[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(float variable) {
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Float variable) {
			Util.checkNullArgumentAndThrow(variable, IllegalNullArguementException.formatEceptionMessage("variable"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull float[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Float[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull String variable) {
			Util.checkNullArgumentAndThrow(variable, IllegalNullArguementException.formatEceptionMessage("variable"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull String[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Object variable) {
			Util.checkNullArgumentAndThrow(variable, IllegalNullArguementException.formatEceptionMessage("variable"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Object[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}
	}

	/**
	 * Template for overloading methods
	 * 
	 * @author Jake Botka
	 *
	 */
	private static class TemplatePair {
		public void max(byte value1, byte value2) {
			throw new NotImplementedYetException();
		}

		public void max(@NonNull Byte value1, @NonNull Byte value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull byte[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Byte[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(short value1, short value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Short value1, @NonNull Short value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull short[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Short[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(int value1, int value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Integer value1, @NonNull Integer value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull int[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Integer[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(long value1, long value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Long value1, @NonNull Long value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull long[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Long[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(double value1, double value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Double value1, @NonNull Double value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull double[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Double[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(float value1, float value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Float value1, float value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull float[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Float[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(@NonNull String value1, @NonNull String value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull String[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Object value1, @NonNull Object value2) {
			throw new NotImplementedYetException();

		}

		public void max(@NonNull Object[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public static short sum(short x1, short x2) {
			return (short) (x1 + x2);
		}
	}
	
	/**
	 * 
	 * @author Jake Botka
	 *
	 */
	private static class ArrayTemplate {

		public void methodcall(@NonNull byte[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Byte[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull short[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Short[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull int[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Integer[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull long[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Long[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}


		public void methodcall(@NonNull double[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Double[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}


		public void methodcall(@NonNull float[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}

		public void methodcall(@NonNull Float[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}


		public void methodcall(@NonNull String[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}


		public void methodcall(@NonNull Object[] arr) {
			Util.checkNullArgumentAndThrow(arr, IllegalNullArguementException.formatEceptionMessage("arr"));
			throw new NotImplementedYetException();

		}
	}

}
