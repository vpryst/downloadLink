package org.vpryst.downloadLink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author vpryst
 */
public class ConnectionManager {

    private CloseableHttpClient httpClient = null;

    private final Logger logger = Logger.getLogger(ConnectionManager.class);

    private final String loginFieldName = "name";
    private final String passwordFieldName = "pass";
    private final String formIdFieldName = "form_id";
    private final String formIdFieldValue = "user_login";
    private final String userLogin = "/user/";
    private final int statusLogin = 302;

    private HttpPost httpPost;
    private CloseableHttpResponse responseAutentificate;
    private HttpHost proxy;
    private RequestConfig config;

    public ConnectionManager() {
        // Default Constructor
    }

    /**
     * create HttpClien
     * 
     * @return HttpClient if not null
     */
    public CloseableHttpClient getConnection() {
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }

    /**
     * Autentificate at the site
     * 
     * @param url
     * @param loginName
     * @param password
     * @return HttpClient
     */
    public boolean autentificate(String url, String loginName, String password) {
        boolean checkAutentificate = false;
        /*proxy =
            new HttpHost(Messager.getString("org.vpryst.downloadLink.ParserHtml.proxy"), Integer.valueOf(Messager
                .getString("org.vpryst.downloadLink.ParserHtml.port")), "http");
        config = RequestConfig.custom().setProxy(proxy).build();*/
        getConnection();
        logger.info(Messager.getString("org.vpryst.download.ConnectionUrl.authenticate"));
        httpPost = new HttpPost(url + userLogin);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair(loginFieldName, loginName));
        nameValuePairs.add(new BasicNameValuePair(passwordFieldName, password));
        nameValuePairs.add(new BasicNameValuePair(formIdFieldName, formIdFieldValue));
        try {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //httpPost.setConfig(config);
                responseAutentificate = httpClient.execute(httpPost);
                if (responseAutentificate.getStatusLine().getStatusCode() == statusLogin) {
                    checkAutentificate = true;
                }

            } finally {
                EntityUtils.consume(responseAutentificate.getEntity());
                responseAutentificate.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        System.out.println(checkAutentificate);
        return checkAutentificate;
    }

    public void closeHttpClien() {
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
