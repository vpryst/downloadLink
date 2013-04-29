package org.vpryst.downloadLink;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author vpryst
 *
 */
public class Messager {
    private static final String mesengerFileName = "message";
    private static final ResourceBundle resourceFile = ResourceBundle.getBundle(mesengerFileName);

    /**
     * Return Value by key 
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
    public static String setString(String key, String value) {
        
        return "";
    }
}
