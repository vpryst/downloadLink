package org.vpryst.downloadLink;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author vpryst
 */
public class WriteFile {

	private static final String LOGIN_NAME = "org.vpryst.downloadLink.WriteFile.LoginName";
	private static final String PASSWORD_NAME = "org.vpryst.downloadLink.WriteFile.PasswordName";
	
    private HashMap<String, String> map = new HashMap<String, String>();
    private Iterator<Entry<String, String>> iteratrLink = null;
    private ConnectionManager connection = new ConnectionManager();
    private boolean check;
    /**
     * @param name
     * @param password
     */
    public WriteFile(String name, String password) {
    	getLoginPassword(name, password);
       check = connection.autentificate(Messager.getString("org.vpryst.downloadLink.WriteFile.link"), name, password);
    }
    
    public void getLoginPassword(String name, String password) {
    	String loginName;
        if (name.equals("") || password.equals("")) {
        	if (Messager.getString(LOGIN_NAME).equals("") || Messager.getString(PASSWORD_NAME).equals("")) {
        		try {
					 //System.in.read();
					System.out.println(System.in.read());
					
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
    }

    /**
     *
     */
    public void getLinks() {
        ParserHtml parse = new ParserHtml(Messager.getString("org.vpryst.downloadLink.WriteFile.link"));
        map = parse.createNameLinkMap();
    }

    /**
     *
     */
    public void fetchFile() {
        FileFetcher file = new FileFetcher(connection.getConnection());
        iteratrLink = map.entrySet().iterator();
        try {
            while (iteratrLink.hasNext()) {
                Entry<String, String> keyValue = iteratrLink.next();
                String key = keyValue.getKey();
                String value = keyValue.getValue();

                file.fileDataLink(key, value);
            }
        } finally {
            connection.closeHttpClien();
        }
    }
}
