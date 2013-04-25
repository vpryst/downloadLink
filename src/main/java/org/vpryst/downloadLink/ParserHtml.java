package org.vpryst.downloadLink;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserHtml {

    private final String classTr = "td-download";
    private final String tag = "a";
    private final String fileLink = "href";
    private final String fileName = "onclick";
    private final String indexOfFrom = "\",\"";
    private final int countFrom = 3;
    private final int countTo = 3;
    private Document doc = null;
    private HashMap<String, String> LinkMap = new HashMap<String, String>();
    private String url = "";

    /**
     * 
     * @param url
     */
    public ParserHtml(String url) {
        this.url = url;
        System.setProperty("http.proxyHost", Messager.getString("org.vpryst.downloadLink.ParserHtml.proxy"));
        System.setProperty("http.proxyPort", Messager.getString("org.vpryst.downloadLink.ParserHtml.port"));

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @return
     */
    public HashMap<String, String> createNameLinkMap() {
        Elements trElements = doc.getElementsByClass(classTr);
        for (Element el : trElements) {
            Elements aElements = el.getElementsByTag(tag);
            for (Element aElement : aElements) {
                int from, to;
                String keyMap = aElement.attr(fileLink);
                String temp = aElement.attr(fileName);
                String valueMap = "";

                from = temp.indexOf(indexOfFrom) + countFrom;
                to = temp.length() - countTo;
                valueMap = temp.substring(from, to).replace(" ", "_");
                LinkMap.put(url + keyMap, valueMap);
            }
        }
        return LinkMap;
    }
}
