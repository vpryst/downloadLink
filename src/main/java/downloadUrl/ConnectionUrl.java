package downloadUrl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ConnectionUrl {

    private final String DEFAULT_USER = "Anonim123";
    private final String DEFAULT_PASSWORD = "123456";
    public static final String Url = "http://refcardz.dzone.com";
    private String getReadLine;
    private HashSet<String> links = new HashSet<String>();

    private CloseableHttpClient httpclient;
    private HttpGet httpget;
    private HttpPost post;

    private CloseableHttpResponse responseAutentificate;
    private CloseableHttpResponse responseGetData;
    private InputStreamReader inputDataStream = null;
    private BufferedReader bufferedDataReader;

    private FileOutputStream fileOutput = null;
    private OutputStreamWriter outputWriter;
    private BufferedWriter bufferWriter;

    public ConnectionUrl() {
        httpclient = HttpClients.createDefault();

    }

    public HashSet<String> getUrlLink() {
        try {
            try {
                httpget = new HttpGet(Url);
                responseGetData = httpclient.execute(httpget);

                System.out.println(Url);
                System.out.println(httpget.getRequestLine());
                System.out.println(httpget.getURI());
                System.out.println("HTTP HEADS");
                Header[] heds = httpget.getAllHeaders();
                for (Header hr : heds) {
                    System.out.println("Name: " + hr.getName() + "     Value: " + hr.getValue());
                }
                System.out.println("Response HEADS");
                Header[] headers = responseGetData.getAllHeaders();
                for (Header hr : headers) {
                    System.out.println("Name: " + hr.getName() + "     Value: " + hr.getValue());
                }
                inputDataStream = new InputStreamReader(responseGetData.getEntity().getContent());

                bufferedDataReader = new BufferedReader(inputDataStream);

                while ((getReadLine = bufferedDataReader.readLine()) != null) {
                    int from, to;
                    if ((from = getReadLine.indexOf("/assets/request")) >= 0) {
                        to = getReadLine.indexOf("direct=true") + 11;
                        System.out.println(getReadLine);
                        System.out.println(from + " " + to);
                        System.out.println(getReadLine.substring(from, to));
                        links.add(Url + getReadLine.substring(from, to));
                    }
                    // System.out.println(getReadLine);
                }
            } finally {
                EntityUtils.consume(responseGetData.getEntity());
                responseGetData.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    public void saveFiles(String URL, String fileName) {
        try {
            try {
                httpget = new HttpGet(URL);

                responseGetData = httpclient.execute(httpget);
                int tmp;
                String extention = "";

                System.out.println(URL);
                System.out.println(httpget.getRequestLine());
                System.out.println(httpget.getURI());
                System.out.println("HTTP HEADS");
                Header[] heds = httpget.getAllHeaders();
                for (Header hr : heds) {
                    System.out.println("Name: " + hr.getName() + "     Value: " + hr.getValue());
                }
                System.out.println("Response HEADS");
                Header[] headers = responseGetData.getAllHeaders();
                for (Header hr : headers) {
                    System.out.println("Name: " + hr.getName() + "     Value: " + hr.getValue() + "*");
                }
                if (responseGetData.getFirstHeader("Content-Type") != null &&
                    responseGetData.getFirstHeader("Content-Type").getValue().indexOf("application/pdf") >= 0) {
                    extention = ".pdf";
                } else {
                    extention = ".html";
                }
                inputDataStream = new InputStreamReader(responseGetData.getEntity().getContent());
                bufferedDataReader = new BufferedReader(inputDataStream);

                fileOutput = new FileOutputStream(fileName + extention);
                outputWriter = new OutputStreamWriter(fileOutput);
                bufferWriter = new BufferedWriter(outputWriter);
                System.out.println("Check before while");
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
            e.printStackTrace();
        }
    }

    public void setAutentificate() {
        post = new HttpPost("http://refcardz.dzone.com/user/");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("name", "Anonim123"));
        nameValuePairs.add(new BasicNameValuePair("pass", "123456"));
        nameValuePairs.add(new BasicNameValuePair("form_id", "user_login"));
        try {
            try {
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                responseAutentificate = httpclient.execute(post);
                System.out.println(responseAutentificate.getStatusLine());
            } finally {
                EntityUtils.consume(responseAutentificate.getEntity());
                responseAutentificate.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setManagerShutDown() {

        try {
            bufferedDataReader.close();
            inputDataStream.close();
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
