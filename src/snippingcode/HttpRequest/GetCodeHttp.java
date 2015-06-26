package snippingcode.HttpRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import snippingcode.service.createJsonoObject;
import snippingcode.service.parseJsonObject;

public class GetCodeHttp {
	private createJsonoObject jsonCreate = new createJsonoObject();
	private parseJsonObject jsonParse = new parseJsonObject();
	private final String targetUrl = "http://localhost:8080/CodeSnipping/File/viewPlugin";
	URL url = null;
	String urlParameters = "";

	public HttpURLConnection configConnection() throws IOException {
		HttpURLConnection connection = null;
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Length",
				Integer.toString(urlParameters.getBytes().length));
		connection.setRequestProperty("Content-Language", "en-US");

		connection.setUseCaches(false);
		connection.setDoOutput(true);
		return connection;
	}

	public void getCode(String username, String pass) throws IOException,
			JSONException {

		urlParameters = "username=" + username + "&password=" + pass
				+ "&number=0";
		url = new URL(targetUrl);

		HttpURLConnection connection = configConnection();

		// Send request
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.close();

		// Get Response
		InputStream is = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		StringBuilder response = new StringBuilder(); // or StringBuffer if not
														// Java 5+
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');
		}
		rd.close();
		JSONArray json = new JSONArray(response.toString());
		jsonParse.parseJsonArray(json);
	}

}
