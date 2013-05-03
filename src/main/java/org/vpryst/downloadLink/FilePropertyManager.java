package org.vpryst.downloadLink;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author vpryst
 */
public class FilePropertyManager {
    private static final String FILE_PATH = "src/main/resources/settings.properties";
    private static final String MESSAGE_FILE_NAME = "message";
    private static final String PROPERTY_FILE_NAME = "settings";
    private static final String COMMAND_LINE_FILE_NAME = "commandLine";

    private static final ResourceBundle RESOURCE_MESSAGE_FILE = ResourceBundle.getBundle(MESSAGE_FILE_NAME);
    private static final ResourceBundle RESOURCE_PROPERTY_FILE = ResourceBundle.getBundle(PROPERTY_FILE_NAME);
    private static final ResourceBundle RESOURCE_COMMAND_LINE_FILE = ResourceBundle.getBundle(COMMAND_LINE_FILE_NAME);

    /**
     * Return Value by key
     * 
     * @param key
     * @return
     */
    public static String getMessageString(String key) {
        try {
            return RESOURCE_MESSAGE_FILE.getString(key);
        } catch (MissingResourceException e) {
            return "";
        }
    }

    /**
     * @param key
     * @return
     */
    public static String getPropertyString(String key) {
        try {
            return RESOURCE_PROPERTY_FILE.getString(key);
        } catch (MissingResourceException e) {
            return "";
        }
    }

    /**
     * @param key
     * @return
     */
    public static String getCommandLineString(String key) {
        try {
            return RESOURCE_COMMAND_LINE_FILE.getString(key);
        } catch (MissingResourceException e) {
            return "";
        }
    }

    public static void setProperty(String key, String value) {
        Properties property = new Properties();

        File file = new File(FILE_PATH);
        FileOutputStream writeProperty = null;
        FileInputStream readProperty = null;
        
        try {
            try {
                readProperty = new FileInputStream(file);
                property.load(readProperty);

            } finally {
                readProperty.close();
            }
            try {
                writeProperty = new FileOutputStream(file);
                property.setProperty(key, value);
                property.store(writeProperty, null);
            } finally {
                writeProperty.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
