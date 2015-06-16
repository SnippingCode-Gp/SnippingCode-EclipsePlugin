package snippingcode.HttpRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.NameValuePair;

import snippingcode.domain.StringType;
import snippingcode.domain.UserDomain;
import snippingcode.service.createJsonoObject;
import snippingcode.service.parseJsonObject;

public class LoginHttpRequest {

	private createJsonoObject jsonCreate = new createJsonoObject();
	private parseJsonObject jsonParse = new parseJsonObject();
	private String url = "http://localhost:8080/CodeSnipping/registration/login";

	public LoginHttpRequest() {
	}

	public JSONObject loginHttpRequest(String username, String password)
			throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		JSONObject json = jsonCreate.createLoginJsonObject(username, password);
		URI urls = new URI(url);
		HttpPost post = new HttpPost(urls);
		StringEntity se = new StringEntity(json.toString());
		se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(se);
		response = client.execute(post);
		JSONObject returnObject = parseInputStream(response);
		UserDomain.setUsername(username);
		UserDomain.setPassword(password);
		if (returnObject != null)
			return returnObject;
		return null;
	}

	private JSONObject parseInputStream(HttpResponse response) {

		try {
			JSONObject JsonObject = new JSONObject(
					EntityUtils.toString(response.getEntity()));
			return JsonObject;
		} catch (Exception e) {
			System.out.println("error " + e.toString());
		}
		return null;
	}

}
