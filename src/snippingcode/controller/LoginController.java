package snippingcode.controller;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import snippingcode.HttpRequest.LoginHttpRequest;
import snippingcode.domain.StringType;
import snippingcode.domain.UserDomain;
import snippingcode.service.LoginFileService;
import snippingcode.service.createJsonoObject;
import snippingcode.service.parseJsonObject;

public class LoginController {

	private static LoginHttpRequest loginHttpRequest;
	private static JSONObject userValidation;
	private static UserDomain userDomain;

	public LoginController() {
		loginHttpRequest = new LoginHttpRequest();
		userDomain = new UserDomain();
	}

	public boolean login(String username, String password) {
		try {
			userValidation = loginHttpRequest.loginHttpRequest(username,
					password);
		} catch (Exception e) {
			System.out.println("loginHttp :: " + e.toString());
			return false;
		}
		userDomain.setPassword(password);
		userDomain.setUsername(username);
		try {
			saveToFile(userDomain.toJson());
		} catch (JSONException e) {
			System.out.println("userDomain ToObject :: " + e.toString());
		}
		return true;
	}

	public boolean checkCache() {
		if (getFromFile() == null) {
			return false;
		}
		String str;
		try {
			str = userValidation.get("username").toString();
			UserDomain.setUsername(str.substring(2, str.length() - 2));
			str = userValidation.get("password").toString();
			UserDomain.setPassword(str.substring(2, str.length() - 2));
		} catch (JSONException e) {
			System.out.println("error :: " + e.toString());
		}
		return true;
	}

	public JSONObject getFromFile() {
		try {
			userValidation = LoginFileService.getFromFile();
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		System.out.println(userValidation.toString());
		return userValidation;
	}

	public void saveToFile(JSONObject userjsonObject) {
		try {
			LoginFileService.saveToFile(userjsonObject);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

}
