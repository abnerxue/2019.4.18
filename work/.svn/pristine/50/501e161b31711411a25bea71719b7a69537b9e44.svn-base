package ck;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MessageUtil {
	//convert To String
	public static String convertToString(Object obj,String charset) throws IOException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(obj);
		String str = bo.toString(charset);
		bo.close();
		oo.close();
		return str;
	}
	 
	//convert To Message
	public static Object convertToMessage(byte[] bytes) throws Exception{
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream sIn = new ObjectInputStream(in);
		return sIn.readObject();
	}
}
