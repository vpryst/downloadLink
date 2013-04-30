package org.vpryst.downloadLink;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author vpryst
 */
public class WriteFile {

    private Map<String, String> map = new HashMap<String, String>();
    private Iterator<Entry<String, String>> iteratrLink = null;
    private ConnectionManager connection;
    private boolean toFetch = false;

    public WriteFile(ConnectionManager conectionManager) {
        this.connection = conectionManager;
    }

    /**
     * Fetch file or write links from Map
     */
    public void fetchFile(boolean toFetch) {
        this.toFetch = toFetch;
        ParserHtml parse = new ParserHtml(FilePropertyManager.getPropertyString("WriteFile.link"));
        map = parse.createNameLinkMap();

        FileFetcher file = new FileFetcher(connection);
        iteratrLink = map.entrySet().iterator();
        try {
            while (iteratrLink.hasNext()) {
                Entry<String, String> keyValue = iteratrLink.next();
                String key = keyValue.getKey();
                String value = keyValue.getValue();

                if (this.toFetch) {
                    file.fileDataLink(key);
                } else {
                    file.fileDataSave(key, value);
                }
            }
        } finally {
            connection.closeHttpClien();
        }
    }
}
