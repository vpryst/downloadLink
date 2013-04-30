package org.vpryst.downloadLink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author vpryst save file by provided Link
 */
public class FileFetcher {

    private final Logger LOGGER = Logger.getLogger(FileFetcher.class);

    /**
     * Response Header name
     */
    private final String CONTENT_TYPE = "Content-Type";
    /**
     * Response Header value
     */
    private final String APLICATION_TYPE = "application/pdf";
    /**
     * Response Header name
     */
    private final String CONTENT_TYPE_LOCATION = "Location";
    /**
     * Extension of files
     */
    private final String TYPE_PDF = ".pdf";
    private final String TYPE_HTML = ".html";

    private ConnectionManager connection = null;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private CloseableHttpResponse responseGetData;

    private InputStreamReader inputDataStream;
    private BufferedReader bufferedDataReader;
    private FileOutputStream fileOutput;
    private OutputStreamWriter outputWriter;
    private BufferedWriter bufferWriter;

    /**
     * @param connection
     */
    public FileFetcher(ConnectionManager connection) {
        this.connection = connection;
    }

    /**
     * @param contentTypeHtml String what header was receive
     * @return extension what is received
     */
    public String getFileExtension(String contentTypeHtml) {
        if (contentTypeHtml.equals(APLICATION_TYPE)) {
            return TYPE_PDF;
        } else {
            return TYPE_HTML;
        }
    }

    /**
     * Take data from provided Link
     * 
     * @param url
     * @param fileName
     */
    public void fileDataSave(String url, String fileName) {
        String header = "";
        LOGGER.info(FilePropertyManager.getMessageString("ConnectionUrl.startDownload") + " " + url + " " + fileName);
        try {
            httpGet = new HttpGet(url);
            httpGet.setConfig(connection.proxySetting());
            responseGetData = connection.getConnection().execute(httpGet);
            if (responseGetData.getFirstHeader(CONTENT_TYPE) != null) {
                header = responseGetData.getFirstHeader(CONTENT_TYPE).getValue();
            }
            inputDataStream = new InputStreamReader(responseGetData.getEntity().getContent(), "ISO-8859-1");
            bufferedDataReader = new BufferedReader(inputDataStream);
            savefile(fileName + getFileExtension(header));
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }

    /**
     * save data into file
     * 
     * @param fileName
     */
    private void savefile(String fileName) {
        int symbolRead;
        try {
            try {
                fileOutput = new FileOutputStream(fileName);
                outputWriter = new OutputStreamWriter(fileOutput, "ISO-8859-1");
                bufferWriter = new BufferedWriter(outputWriter);
                while ((symbolRead = bufferedDataReader.read()) != -1) {
                    bufferWriter.write(symbolRead);
                }
            } finally {
                bufferedDataReader.close();
                inputDataStream.close();

                bufferWriter.close();
                outputWriter.close();
                fileOutput.close();

                EntityUtils.consume(responseGetData.getEntity());
                responseGetData.close();
                LOGGER.info(FilePropertyManager.getMessageString("ConnectionUrl.endtDownload"));
            }
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }

    /**
     * Take url and provide url
     * 
     * @param url
     * @param fileName
     */
    public void fileDataLink(String url) {
        try {
            try {
                httpPost = new HttpPost(url);
                httpPost.setConfig(connection.proxySetting());
                responseGetData = connection.getConnection().execute(httpPost);
                if (responseGetData.getFirstHeader(CONTENT_TYPE_LOCATION) != null) {
                    LOGGER.info(responseGetData.getFirstHeader(CONTENT_TYPE_LOCATION).getValue());
                    System.out.println(responseGetData.getFirstHeader(CONTENT_TYPE_LOCATION).getValue());
                } else {
                    System.out.println(FilePropertyManager.getMessageString("org.vpryst.downloadLink.FileFetcher.ErrorGetLink") + url);
                }
            } finally {
                EntityUtils.consume(responseGetData.getEntity());
                responseGetData.close();
            }

        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }

}