package snippingcode.service;

import org.json.JSONObject;

public class createJsonoObject {

	public JSONObject createLoginJsonObject(String username, String password) {
		JSONObject json = new JSONObject();
		try {
			json.put("username", username);
			json.put("password", password);
		} catch (Exception e) {
			System.out.println("not work json see error");
			System.out.println(e.toString());
		}
		return json;
	}

	public createJsonoObject() {
	}

}
