package ck.preprocessorData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <h3>序列化工具类</h3>
 * 
 * @author LIUJUNWU
 * @version 1.0.0
 */
public class SerializeUtil {
	/*
	 * 序列化  
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			//序列化  .toByteArray(); 
			baos = new  ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return null;
		
	}
	
	/*
	 * 反序列化  
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			//反序列化  
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
