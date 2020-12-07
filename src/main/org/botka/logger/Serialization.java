/**
 * Serialization.java
 * Programmer: Jake Botka
 * Nov 9, 2020
 *
 */
package main.org.botka.logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class with static operations. 
 * There are also encapsulated objects for added security and ecapulation with Serializer.java and Deserializer.java
 * @author Jake Botka
 *
 */
public class Serialization {

	/**
	 * 
	 * @param data of object
	 * @return
	 */
	public static byte[] serailizeObject(Object obj) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			ObjectOutputStream out = new ObjectOutputStream(stream);
			out.writeObject(obj);
			return stream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static boolean serializeToFIle(byte[] data, File file) {
		boolean flag = false;
		if (data != null && file != null) {
			ObjectOutputStream objOut = null;
			try {
				 objOut = new ObjectOutputStream(new FileOutputStream(file));
				objOut.write(data);
				objOut.flush();
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (objOut != null) {
					try {
						objOut.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
		
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] data) throws ClassNotFoundException, IOException {
		Object obj = deserailizeObject(data);
		if (obj != null)
			return ((T) obj);

		return null;
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserailizeObject(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream stream = new ByteArrayInputStream(data);
		ObjectInputStream in = new ObjectInputStream(stream);
		if (in.available() > 0)
			return in.readObject();
		return null;

	}
}
