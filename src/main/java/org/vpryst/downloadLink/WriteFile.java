package org.vpryst.downloadLink;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author vpryst
 */
public class WriteFile {

    private HashMap<String, String> map = new HashMap<String, String>();
    private Iterator<Entry<String, String>> iteratrLink = null;
    private ConnectionManager connection = new ConnectionManager();

    /**
     * @param name
     * @param password
     */
    public WriteFile(String name, String password) {
        connection.autentificate(Messager.getString("org.vpryst.downloadLink.WriteFile.link"), name, password);
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
        while (iteratrLink.hasNext()) {
            Entry<String, String> keyValue = iteratrLink.next();
            String key = keyValue.getKey();
            String value = keyValue.getValue();

            file.fileData(key, value);
        }
    }
}
