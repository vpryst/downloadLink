package org.vpryst.downloadLink;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserHtml implements ConstVariables {

    private final Logger LOGGER = Logger.getLogger(ParserHtml.class);

    /**
     * Class of tag "tr"
     */
    private final String CLASS_TR = "td-download";
    /**
     * Name of tag
     */
    private final String TAG = "a";
    /**
     * Parameter to take name of file
     */
    private final String FILE_LINK = "href";
    /**
     * Parameter to take link
     */
    private final String FILE_NAME = "onclick";
    /**
     * for take link
     */
    private final String INDEX_OF_FROM = "\",\"";
    /**
     * count symbols for substring
     */
    private final int COUNT_FROM = 3;
    private final int COUNT_TO = 3;
    private Document doc = null;
    private Map<String, String> linkMap = new HashMap<String, String>();
    private String url = "";

    /**
     * Take page from Url
     * 
     * @param url
     */
    public ParserHtml(String url) {
        this.url = url;
        /**
         * Set system property for proxy connection
         */
        System.setProperty(CONNECTION_PROXY_KEY, CONNECTION_PROXY);
        System.setProperty(CONNECTION_PORT_KEY, CONNECTION_PORT);

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }

    /**
     * Create Map of links and names of files
     * 
     * @return Map<String,String>
     */
    public Map<String, String> createNameLinkMap() {
        Elements trElements = doc.getElementsByClass(CLASS_TR);
        for (Element el : trElements) {
            Elements aElements = el.getElementsByTag(TAG);
            for (Element aElement : aElements) {
                String keyMap = aElement.attr(FILE_LINK);
                String temp = aElement.attr(FILE_NAME);
                String valueMap = "";

                int from = temp.indexOf(INDEX_OF_FROM) + COUNT_FROM;
                int to = temp.length() - COUNT_TO;
                valueMap = temp.substring(from, to).replace(" ", "_");
                linkMap.put(url + keyMap, valueMap);
            }
        }
        return linkMap;
    }
}
