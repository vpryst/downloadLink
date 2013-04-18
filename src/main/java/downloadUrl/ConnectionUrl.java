package downloadUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class ConnectionUrl {

    private final String DEFAULT_USER = "Anonim123";
    private final String DEFAULT_PASSWORD = "123456";
    public static final String Url = "http://refcardz.dzone.com";

    private DefaultHttpClient httpclient;
    private HttpGet httpget;
    private CookieStore cookieStore;
    private HttpContext localContext;
    private HttpPost post;

    private HttpResponse response;
    private InputStreamReader inputDataStream = null;
    private BufferedReader bufferedDataReader;

    public ConnectionUrl() {
        httpclient = new DefaultHttpClient();
        
    }

    public BufferedReader setUrl(String URL, int i, String tmp) {
        try {
            httpget = new HttpGet(URL);
            //httpget.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.ACCEPT_ALL);
            httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0");
            if (tmp != "Get Links") {
            httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpget.setHeader("Accept-Encoding", "gzip, deflate");
            httpget.setHeader("Accept-Language", "en-US,en;q=0.5");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("Cookie", "SESS374e8db54ec3033c25a586b1d093b1d1=41074f579f0e5qrk2779cm6hc6; wooTracker=eAXwv3gwJmHB; optimizelySegments=%7B%22177785857%22%3A%22false%22%2C%22177840837%22%3A%22referral%22%2C%22177932055%22%3A%22ff%22%7D; optimizelyBuckets=%7B%7D; __gads=ID=0dad8d4e950aa499:T=1364226981:S=ALNI_MZY1wXvyKfGlsamR8q3i9dbAH5pnw; __utma=62263603.1845300023.1366277245.1366277245.1366277245.1; __utmz=62263603.1366277245.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=82130581.994355443.1366036523.1366277255.1366280411.14; __utmz=82130581.1366277255.13.4.utmcsr=dzone.com|utmccn=(referral)|utmcmd=referral|utmcct=/links/index.html; dzexpresstoken=MTEzNTI4MTEwMTgxNDBkODMyMjVi; optimizelyEndUserId=oeu1366116268884r0.5976859505777073; linkedin_oauth_m0tctekctvmc_crc=null; __utmc=62263603; __utmc=82130581; has_js=1; ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE=Anonim123%21v3%212013652828; __utmb=82130581.1.10.1366280411");
            httpget.setHeader("Host", "refcardz.dzone.com");
            httpget.setHeader("If-Modified-Since", "Thu, 18 Apr 2013 10:36:52 GMT");
            httpget.setHeader("Proxy-Authorization", "NTLM TlRMTVNTUAADAAAAGAAYAIAAAAAoASgBmAAAABIAEgBYAAAADAAMAGoAAAAKAAoAdgAAAAAAAADAAQAABYKIogYBsR0AAAAPDAFpVl/U57tkrE6Vep43HFMATwBGAFQAUwBFAFIAVgBFAHYAcAByAHkAcwB0AEkARgAwADMAMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABsZkJnTG7QASLRrUBuDZZuAQEAAAAAAADaLkjxIDzOATN3iCEr62ABAAAAAAIAEgBTAE8ARgBUAFMARQBSAFYARQABAAwAUABSAE8AWABZADEABAAgAHMAbwBmAHQAcwBlAHIAdgBlAGMAbwBtAC4AYwBvAG0AAwAsAHAAcgBvAHgAeQAuAHMAbwBmAHQAcwBlAHIAdgBlAGMAbwBtAC4AYwBvAG0ACAAwADAAAAAAAAAAAAAAAAAwAACj0PmSbOtyPsKb7MAQzbegZvXS1FOxjPhFrtzWFVcRbQoAEAAAAAAAAAAAAAAAAAAAAAAACQAuAEgAVABUAFAALwByAGUAZgBjAGEAcgBkAHoALgBkAHoAbwBuAGUALgBjAG8AbQAAAAAAAAAAAA==");
            httpget.setHeader("Referer", "http://refcardz.dzone.com/");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0");  
            
            }
            System.out.println(tmp);
              
                     
              
                 
                 
                  
                    
                 
                  
            
            
            
            
            /*date: thu, 18 apr 2013 09:28:02 gmt
            server: apache/2.2.11 (unix) dav/2 svn/1.5.5 resin/4.0.4 php/5.2.13
            x-powered-by: php/5.2.13
            expires: sun, 19 nov 1978 05:00:00 gmt
            last-modified: thu, 18 apr 2013 09:28:02 gmt
            cache-control: store, no-cache, must-revalidate, post-check=0, pre-check=0
            vary: accept-encoding,user-agent
            content-encoding: gzip
            content-type: text/html; charset=utf-8
            x-cache: miss from proxy2.softservecom.com
            x-cache-lookup: miss from proxy2.softservecom.com:8080
            via: 1.0 proxy2.softservecom.com:8080 (squid/2.6.stable21)
            proxy-connection: close*/

            
            /*
            http://cdn.dzone.com/sites/all/files/refcardz/rc008-designpatterns_online.pdf
                GET http://cdn.dzone.com/sites/all/files/refcardz/rc008-designpatterns_online.pdf HTTP/1.1
                http://cdn.dzone.com/sites/all/files/refcardz/rc008-designpatterns_online.pdf
                HTTP HEADS
                Name: User-Agent     Value: Mozilla/5.0 (X11; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/12.0
                Name: Content-Type     Value: text/html
                Response HEADS
                Name: Date     Value: Thu, 18 Apr 2013 09:54:16 GMT
                Name: Content-Type     Value: application/pdf
                Name: Content-Length     Value: 2223123
                Name: Connection     Value: keep-alive
                Name: Last-Modified     Value: Wed, 22 Jul 2009 13:58:30 GMT
                Name: ETag     Value: "1152a013-21ec13-46f4bc4184d80"
                Name: Accept-Ranges     Value: none
                Name: Cache-Control     Value: max-age=1
                Name: Expires     Value: Thu, 18 Apr 2013 09:54:17 GMT
                Name: MattAndRick     Value: were here 2
                Name: Server     Value: NetDNA-cache/2.2
                Name: X-Cache     Value: EXPIRED*/
            
            
            while ( i<1000) {
            response = httpclient.execute(httpget);
            //System.out.println(response.);
            
            System.out.println(URL);
            System.out.println(httpget.getRequestLine());
            System.out.println(httpget.getURI());
            System.out.println("HTTP HEADS");
            Header [] heds = httpget.getAllHeaders();
            for (Header hr : heds) {
                System.out.println("Name: " + hr.getName() + "     Value: " + hr.getValue());
            }
            System.out.println("Response HEADS");
            Header [] headers = response.getAllHeaders();
            for (Header hr : headers) {
                System.out.println("Name: " + hr.getName() + "     Value: " + hr.getValue());
            }
            i++;
            }
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
            // nameValuePairs.add(new BasicNameValuePair("form_build_id", ""));
            nameValuePairs.add(new BasicNameValuePair("form_id", "user_login"));
            // nameValuePairs.add(new BasicNameValuePair("op", "Log+in"));
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
