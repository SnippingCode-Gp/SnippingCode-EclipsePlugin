package snippingcode.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import snippingcode.domain.CodeDomain;
import snippingcode.domain.StringType;
import snippingcode.domain.UserDomain;

public class parseJsonObject {
	public StringType parseToStringType(JSONObject json) {
		try {
			StringType stringType = new StringType();
			String string = json.getString("string");
			stringType.setString(string);
			return stringType;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	public void parseJsonArray(JSONArray jsonArray) {
		if(jsonArray == null){
			return ;
		}
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				JSONObject jsonobj = jsonArray.getJSONObject(i);
				CodeDomain codeDomain = new CodeDomain();
				codeDomain.setCode(jsonobj.getString("code"));
				codeDomain.setId(jsonobj.getInt("id"));
				codeDomain.setName(jsonobj.getString("name"));
				codeDomain.setType(jsonobj.getString("type"));
				codeDomain.setUsername(jsonobj.getString("username"));
				UserDomain.addCode(codeDomain);
			} catch (JSONException e) {
				System.out.println(e.toString());
			}
		}
	}
}
