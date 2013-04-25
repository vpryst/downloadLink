package org.vpryst.downloadLink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author vpryst save file by provided Link
 */
public class FileFetcher {

    private final Logger logger = Logger.getLogger(FileFetcher.class);

    private final String contentType = "Content-Type";
    private final String applicationType = "application/pdf";
    private final String typePdf = ".pdf";
    private final String typeHtml = ".html";

    private CloseableHttpClient httpClient = null;
    private HttpGet httpGet;
    private CloseableHttpResponse responseGetData;

    private InputStreamReader inputDataStream;
    private BufferedReader bufferedDataReader;
    private FileOutputStream fileOutput;
    private OutputStreamWriter outputWriter;
    private BufferedWriter bufferWriter;

    /**
     * @param httpClient
     */
    public FileFetcher(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @param contentType String what header was receive
     * @return extension what is received
     */
    public String getFileExtension(final String contentType) {
        if (contentType.equals(applicationType)) {
            return typePdf;
        } else {
            return typeHtml;
        }
    }

    /**
     * Take data from provided Link
     * 
     * @param url
     * @param fileName
     */
    public void fileData(String url, String fileName) {
        String header = "";
        logger.info(Messager.getString("org.vpryst.download.ConnectionUrl.startDownload"));
        try {
            httpGet = new HttpGet(url);
            responseGetData = httpClient.execute(httpGet);
            if (responseGetData.getFirstHeader(contentType) != null) {
                header = contentType;
            }
            inputDataStream = new InputStreamReader(responseGetData.getEntity().getContent());
            bufferedDataReader = new BufferedReader(inputDataStream);
            savefile(fileName + getFileExtension(header));
        } catch (IOException e) {
            logger.error(e.getMessage());
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
                outputWriter = new OutputStreamWriter(fileOutput);
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
                logger.info(Messager.getString("org.vpryst.download.ConnectionUrl.endtDownload"));
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        
    }
}
