package org.vpryst.downloadLink;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author vpryst
 * 
 */
public class Messager {
	private static final String extention = ".properties";
	private static final String mesengerFileName = "message";
	private static final ResourceBundle resourceFile = ResourceBundle
			.getBundle(mesengerFileName);

	/**
	 * Return Value by key
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return resourceFile.getString(key);
		} catch (MissingResourceException e) {
			return "";
		}
	}

	public static void setString(String key, String value) throws IOException {
		Properties property = new Properties();
		File fileProperty = new File(mesengerFileName + extention);
		InputStream in = new FileInputStream(fileProperty);
		property.load(in);
		property.put(key, value);
		OutputStream out = new FileOutputStream(fileProperty);
		property.store(out, "properties");
	}
	
}
