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
 */
public class ConnectionUrl {

    private final Logger LOGGER_CONNECTION_URL = Logger.getLogger(ConnectionUrl.class);
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

    /**
     * This method take Links from provided Url. And return Map of Links
     * @param url
    **/
    public HashSet<String> getUrlLink(String url) {
        LOGGER_CONNECTION_URL.info(Messager.getString("org.vpryst.download.getLinks"));
        try {
            try {
                httpGet = new HttpGet(url);
                responseGetData = httpClient.execute(httpGet);
                inputDataStream = new InputStreamReader(responseGetData.getEntity().getContent());
                bufferedDataReader = new BufferedReader(inputDataStream);
                while ((readLine = bufferedDataReader.readLine()) != null) {
                    int from, to;
                    if ((from = readLine.indexOf(Messager.getString("org.vpryst.download.takeLinkSubstringFrom"))) >= 0) {
                        to = readLine.indexOf(Messager.getString("org.vpryst.download.takeLinkSubstringTo")) + 11;
                        links.add(url + readLine.substring(from, to));
                    }
                }
            } finally {
                EntityUtils.consume(responseGetData.getEntity());
                responseGetData.close();
            }
        } catch (IOException e) {
            // e.printStackTrace();
            LOGGER_CONNECTION_URL.error(e.getMessage());
        }
        return links;
    }

    /**
     * Mathod save files from link, and set name of file. Extention set from response header
     */
    public void saveFiles(String url, String fileName) {
        LOGGER_CONNECTION_URL.info(Messager.getString("org.vpryst.download.ConnectionUrl.startDownload"));
        try {
            try {
                httpGet = new HttpGet(url);
                responseGetData = httpClient.execute(httpGet);
                int tmp;
                String extention = "";
                if (responseGetData.getFirstHeader(Messager.getString("org.vpryst.download.ConnectionUrl.contentType")) != null 
                    && responseGetData.getFirstHeader(Messager.getString("org.vpryst.download.ConnectionUrl.contentType"))
                        .getValue().indexOf(Messager.getString("org.vpryst.download.ConnectionUrl.aplicationType")) >= 0) {
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
            LOGGER_CONNECTION_URL.error(e.getMessage());
        }
        LOGGER_CONNECTION_URL.info(Messager.getString("org.vpryst.download.ConnectionUrl.endtDownload"));
    }

    /**
     * authentificate user on web page
    **/
    public void autentificate(String url, String loginName, String password) {
        LOGGER_CONNECTION_URL.info(Messager.getString("org.vpryst.download.ConnectionUrl.authenticate"));
        httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("name", loginName));
        nameValuePairs.add(new BasicNameValuePair("pass", password));
        nameValuePairs.add(new BasicNameValuePair("form_id", "user_login"));
        try {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                responseAutentificate = httpClient.execute(httpPost);
                System.out.println(responseAutentificate.getStatusLine());
            } finally {
                EntityUtils.consume(responseAutentificate.getEntity());
                responseAutentificate.close();
            }
        } catch (IOException e) {
            // e.printStackTrace();
            LOGGER_CONNECTION_URL.error(e.getMessage());
        }
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
            LOGGER_CONNECTION_URL.error(e.getMessage());
        }

    }
}
