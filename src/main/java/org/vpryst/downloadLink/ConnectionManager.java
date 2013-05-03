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
 * Create connection to Website
 * 
 * @author vpryst
 */
public class ConnectionManager implements ConstVariables {

    private final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

    /**
     * Tag input Id
     */
    private final String LOGIN_FIELD_NAME = "name";

    /**
     * Tag input Id
     */
    private final String PASSWORD_FIELD_NAME = "pass";

    /**
     * Hidden tag input id
     */
    private final String FORM_ID_FIELD_NAME = "form_id";
    /**
     * Hidden tag input value
     */
    private final String FORM_ID_FIELD_VALUE = "user_login";
    /**
     * Path to user Login
     */
    private final String USER_LOGIN = "/user/";
    /**
     * Http Status for User login
     */
    private final int STATUS_LOGIN = 302;

    private final String TYPE_CONNECTION = "http";

    private CloseableHttpClient httpClient = null;
    private HttpPost httpPost;
    private CloseableHttpResponse responseAutentificate;
    private HttpHost proxy;
    private RequestConfig config;
    private String user;
    private String pass;

    /**
     * Take user and password and provide Authorized
     * 
     * @param user
     * @param pass
     */
    public ConnectionManager(String user, String pass) {
        this.user = user;
        this.pass = pass;
        autentificate(PROPERTY_LINK);
    }

    public ConnectionManager(String user, String pass, int i) {
        this.user = user;
        this.pass = pass;
    }

    public ConnectionManager() {
        httpClient = null;
    }

    /**
     * create HttpClien
     * 
     * @return HttpClient
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
     * @return HttpClient
     */
    public boolean autentificate(String url) {
        UrlEncodedFormEntity urlEncodeParam;
        boolean checkAutentificate = false;

        getConnection();
        LOGGER.info(FilePropertyManager.getMessageString("ConnectionUrl.authenticate"));
        httpPost = new HttpPost(url + USER_LOGIN);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair(LOGIN_FIELD_NAME, user));
        nameValuePairs.add(new BasicNameValuePair(PASSWORD_FIELD_NAME, pass));
        nameValuePairs.add(new BasicNameValuePair(FORM_ID_FIELD_NAME, FORM_ID_FIELD_VALUE));

        try {
            try {
                urlEncodeParam = new UrlEncodedFormEntity(nameValuePairs);
                httpPost.setEntity(urlEncodeParam);
                httpPost.setConfig(proxySetting());
                responseAutentificate = httpClient.execute(httpPost);
                if (responseAutentificate.getStatusLine().getStatusCode() == STATUS_LOGIN) {
                    checkAutentificate = true;
                }

            } finally {
                EntityUtils.consume(responseAutentificate.getEntity());
                responseAutentificate.close();
            }
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
        System.out.println(checkAutentificate);
        return checkAutentificate;
    }

    /**
     * Set proxy setting for HttpPost or HttpGet request
     * 
     * @return config
     */
    public RequestConfig proxySetting() {
        if (!CONNECTION_PROXY.equals("") && !CONNECTION_PORT.equals("")) {
            proxy = new HttpHost(CONNECTION_PROXY, Integer.valueOf(CONNECTION_PORT), TYPE_CONNECTION);
            config = RequestConfig.custom().setProxy(proxy).build();
        } else {
            config = null;
        }
        return config;
    }

    /**
     * Close HttpClient
     */
    public void closeHttpClien() {
        try {
            httpClient.close();
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }
}
