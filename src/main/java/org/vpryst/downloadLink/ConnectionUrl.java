package org.vpryst.downloadLink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * This class provide connection to web page take, links and can save files.
 * 
 * @author vpryst
 */
public class ConnectionUrl {

    private final Logger logger = Logger.getLogger(ConnectionUrl.class);
    private String readLine = "";
    private HashSet<String> links = new HashSet<String>();

    private CloseableHttpClient httpClient;
    private HttpGet httpGet;
    private HttpPost httpPost;

    private CloseableHttpResponse responseAutentificate;
    private CloseableHttpResponse responseGetData;
    private InputStreamReader inputDataStream = null;
    private BufferedReader bufferedDataReader;

    private FileOutputStream fileOutput = null;
    private OutputStreamWriter outputWriter;
    private BufferedWriter bufferWriter;

    public ConnectionUrl() {
        httpClient = HttpClients.createDefault();

    }

    public void saveFiles(String url, String fileName) {
        logger.info(Messager.getString("org.vpryst.download.ConnectionUrl.startDownload"));
        try {
            try {
                httpGet = new HttpGet(url);
                responseGetData = httpClient.execute(httpGet);
                int tmp;
                String extention = "";
                if (responseGetData.getFirstHeader(Messager.getString("org.vpryst.download.ConnectionUrl.contentType")) != null &&
                    responseGetData.getFirstHeader(Messager.getString("org.vpryst.download.ConnectionUrl.contentType")).getValue()
                        .indexOf(Messager.getString("org.vpryst.download.ConnectionUrl.aplicationType")) >= 0) {
                    extention = Messager.getString("org.vpryst.download.ConnectionUrl.pdf");
                } else {
                    extention = Messager.getString("org.vpryst.download.ConnectionUrl.html");
                }
                inputDataStream = new InputStreamReader(responseGetData.getEntity().getContent());
                bufferedDataReader = new BufferedReader(inputDataStream);
                fileOutput = new FileOutputStream(fileName + extention);
                outputWriter = new OutputStreamWriter(fileOutput);
                bufferWriter = new BufferedWriter(outputWriter);
                while ((tmp = bufferedDataReader.read()) != -1) {
                    bufferWriter.write(tmp);
                }
            } finally {
                bufferedDataReader.close();
                inputDataStream.close();
                bufferWriter.close();
                outputWriter.close();
                fileOutput.close();

                EntityUtils.consume(responseGetData.getEntity());
                responseGetData.close();
            }
        } catch (IOException e) {
            // e.printStackTrace();
            logger.error(e.getMessage());
        }
        logger.info(Messager.getString("org.vpryst.download.ConnectionUrl.endtDownload"));
    }

    /**
     * Close all connection HttpClient
     */
    public void managerShutDown() {

        try {
            bufferedDataReader.close();
            inputDataStream.close();
            httpClient.close();
        } catch (IOException e) {
            // e.printStackTrace();
            logger.error(e.getMessage());
        }

    }
}
