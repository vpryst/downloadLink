package downloadUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.SliderUI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ConnectionUrl {

    private final String DEFAULT_USER = "Anonim123";
    private final String DEFAULT_PASSWORD = "123456";
    public static final String Url = "http://refcardz.dzone.com";

    private DefaultHttpClient httpclient;
    private HttpGet httpget;
    private HttpPost post;

    private HttpResponse response;
    private InputStreamReader inputDataStream = null;
    private BufferedReader bufferedDataReader;

    public ConnectionUrl() {
        httpclient = new DefaultHttpClient();
        /*
         * httpclient.getCredentialsProvider().setCredentials(new AuthScope(Url, 8080), new UsernamePasswordCredentials(DEFAULT_USER,
         * DEFAULT_PASSWORD)); System.out.println(httpclient.getCredentialsProvider());
         */
        // setUrl("");

    }

    public BufferedReader setUrl(String URL) {
        try {
            httpget = new HttpGet(URL);
            response = httpclient.execute(httpget);
            System.out.println(response.getStatusLine());
            inputDataStream = new InputStreamReader(response.getEntity().getContent());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedDataReader = new BufferedReader(inputDataStream);
        return bufferedDataReader;
    }

    public BufferedReader setAutentificate() {
        try {
            post = new HttpPost("http://refcardz.dzone.com/user/");
            /*
             * post.addHeader("name", "Anonim123"); post.addHeader("pass", "123456"); post.addHeader("form_build_id", sum);
             * post.addHeader("form_id", "user_login"); post.addHeader("op", "Log+in");
             */

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("name", "Anonim123"));
            nameValuePairs.add(new BasicNameValuePair("pass", "123456"));
            nameValuePairs.add(new BasicNameValuePair("form_build_id", ""));
            nameValuePairs.add(new BasicNameValuePair("form_id", "user_login"));
            nameValuePairs.add(new BasicNameValuePair("op", "Log+in"));
            try {
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            response = httpclient.execute(post);
            inputDataStream = new InputStreamReader(response.getEntity().getContent());
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedDataReader = new BufferedReader(inputDataStream);
        return bufferedDataReader;
    }

    public String getLink(String first) {
        String Link = "";
        try {
            httpget = new HttpGet(Url + first);
            response = httpclient.execute(httpget);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Link;
    }

    public void setManagerShutDown() {
        
        try {
            bufferedDataReader.close();
            inputDataStream.close();
            httpclient.getConnectionManager().shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
