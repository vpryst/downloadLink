package downloadUrl;

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

/*
 * This class provide connection to web page take, links and can save files. 
 */
public class ConnectionUrl {

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

	/*
	 * This method take Links from provided Url. And return Map of Links
	 */
	public HashSet<String> getUrlLink(String URL) {
		Log.LOGGER_CONNECTION_URL.info("Get links from site");
		try {
			try {
				httpGet = new HttpGet(URL);
				responseGetData = httpClient.execute(httpGet);
				inputDataStream = new InputStreamReader(responseGetData
						.getEntity().getContent());
				bufferedDataReader = new BufferedReader(inputDataStream);
				while ((readLine = bufferedDataReader.readLine()) != null) {
					int from, to;
					if ((from = readLine.indexOf("/assets/request")) >= 0) {
						to = readLine.indexOf("direct=true") + 11;
						links.add(URL + readLine.substring(from, to));
					}
				}
			} finally {
				EntityUtils.consume(responseGetData.getEntity());
				responseGetData.close();
			}
		} catch (IOException e) {
			// e.printStackTrace();
			Log.LOGGER_CONNECTION_URL.error(e.getMessage());
		}
		return links;
	}

	/*
	 * Mathod save files from link, and set name of file. Extention set from
	 * response header
	 */
	public void saveFiles(String URL, String fileName) {
		Log.LOGGER_CONNECTION_URL.info("Start download file");
		try {
			try {
				httpGet = new HttpGet(URL);
				responseGetData = httpClient.execute(httpGet);
				int tmp;
				String extention = "";
				if (responseGetData.getFirstHeader("Content-Type") != null
						&& responseGetData.getFirstHeader("Content-Type")
								.getValue().indexOf("application/pdf") >= 0) {
					extention = ".pdf";
				} else {
					extention = ".html";
				}
				inputDataStream = new InputStreamReader(responseGetData
						.getEntity().getContent());
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
			Log.LOGGER_CONNECTION_URL.error(e.getMessage());
		}
		Log.LOGGER_CONNECTION_URL.info("End download file");
	}

	/*
	 * authentificate user on web page
	 */
	public void Autentificate(String URL, String loginName, String password) {
		Log.LOGGER_CONNECTION_URL.info("Autentificate");
		httpPost = new HttpPost(URL);
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
			Log.LOGGER_CONNECTION_URL.error(e.getMessage());
		}
	}

	/*
	 * Close all connection HttpClient
	 */
	public void ManagerShutDown() {

		try {
			bufferedDataReader.close();
			inputDataStream.close();
			httpClient.close();
		} catch (IOException e) {
			// e.printStackTrace();
			Log.LOGGER_CONNECTION_URL.error(e.getMessage());
		}

	}
}
