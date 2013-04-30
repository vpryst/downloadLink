package org.vpryst.downloadLink;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author vpryst
 */
public class FilePropertyManager {
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
}
