package org.vpryst.downloadLink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
    private final String contentTypeLocation = "Location";
    private final String typePdf = ".pdf";
    private final String typeHtml = ".html";

    private CloseableHttpClient httpClient = null;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private CloseableHttpResponse responseGetData;
    private HttpHost proxy;
    private RequestConfig config;
    
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
     * @param contentTypeHtml String what header was receive
     * @return extension what is received
     */
    public String getFileExtension(String contentTypeHtml) {
        if (contentTypeHtml.equals(applicationType)) {
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
    public void fileDataSave(String url, String fileName) {
        /*proxy = new HttpHost(Messager.getString("org.vpryst.downloadLink.ParserHtml.proxy"), Integer.valueOf(Messager
            .getString("org.vpryst.downloadLink.ParserHtml.port")), "http");
        config = RequestConfig.custom().setProxy(proxy).build();*/
        String header = "";
        logger.info(Messager.getString("org.vpryst.download.ConnectionUrl.startDownload") + " " + url + " " + fileName);
        try {
            httpGet = new HttpGet(url);
            //httpGet.setConfig(config);
            responseGetData = httpClient.execute(httpGet);
            
            Header[] headers = responseGetData.getAllHeaders();
            for (int i = 0; i<headers.length; i++) {
                System.out.println(headers[i]);
            }
            if (responseGetData.getFirstHeader(contentType) != null) {
                header = responseGetData.getFirstHeader(contentType).getValue();
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
    
    public void fileDataLink(String url, String fileName) {
        /*
        proxy = new HttpHost(Messager.getString("org.vpryst.downloadLink.ParserHtml.proxy"), Integer.valueOf(Messager
            .getString("org.vpryst.downloadLink.ParserHtml.port")), "http");
        config = RequestConfig.custom().setProxy(proxy).build();*/
        logger.info(Messager.getString("org.vpryst.download.ConnectionUrl.startDownload") + " " + url + " " + fileName);
        try {
            try {
            httpPost = new HttpPost(url);
            //httpPost.setConfig(config);
            responseGetData = httpClient.execute(httpPost);
            if (responseGetData.getFirstHeader(contentTypeLocation) != null) {
                logger.info(responseGetData.getFirstHeader(contentTypeLocation).getValue());
                System.out.println(responseGetData.getFirstHeader(contentTypeLocation).getValue());
            }
            } finally {
                EntityUtils.consume(responseGetData.getEntity());
                responseGetData.close();
            }
            
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    
    
}
