package org.vpryst.downloadLink;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messager {
    private static final String mesengerFileName = "message";
    private static final ResourceBundle resourceFile = ResourceBundle.getBundle(mesengerFileName);

    public static String getString(String key) {
        try {
            return resourceFile.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public static String getString(String key, Object... params) {
        try {
            return MessageFormat.format(resourceFile.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(Messager.getString("org.vpryst.download.takeLinkSubstringFrom"));
    }

}
