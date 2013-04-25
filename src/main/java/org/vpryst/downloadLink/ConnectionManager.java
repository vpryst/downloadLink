package org.vpryst.downloadLink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
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

    private HttpPost httpPost;
    private CloseableHttpResponse responseAutentificate;

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
    public CloseableHttpClient autentificate(String url, String loginName, String password) {
        getConnection();
        logger.info(Messager.getString("org.vpryst.download.ConnectionUrl.authenticate"));
        httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair(loginFieldName, loginName));
        nameValuePairs.add(new BasicNameValuePair(passwordFieldName, password));
        nameValuePairs.add(new BasicNameValuePair(formIdFieldName, formIdFieldValue));
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
            logger.error(e.getMessage());
        }
        return httpClient;
    }
}
