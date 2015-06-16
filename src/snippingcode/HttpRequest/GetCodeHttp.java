package snippingcode.HttpRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
	private String url = "http://localhost:8080/CodeSnipping/File/viewPlugin";

	public void getCode(String username , String pass) throws URISyntaxException,
			ClientProtocolException, IOException {
		System.out.println(username + " " + pass);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		URI urls = new URI(url);
		HttpPost post = new HttpPost(urls);
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", pass));
		params.add(new BasicNameValuePair("number", "0"));
		post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		response = client.execute(post);
		System.out.println(response.toString());
		JSONArray returnObject = parseInputStream(response);
		jsonParse.parseJsonArray(returnObject);
	}

	private JSONArray parseInputStream(HttpResponse response) {

		try {
			String http = EntityUtils.toString(response.getEntity());
			System.out.println(http);
			JSONArray JsonObject = new JSONArray(http);
			return JsonObject;
		} catch (Exception e) {
			System.out.println("error " + e.toString());
		}
		return null;
	}
}
