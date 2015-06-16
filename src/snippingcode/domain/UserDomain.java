package snippingcode.domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDomain {
	private static String username;
	private static String password;
	private static List<CodeDomain> Code = new ArrayList<CodeDomain>();

	public UserDomain() {
	}

	public UserDomain(String u, String p) {
		username = u;
		password = p;
	}

	public UserDomain(String u, String p, ArrayList<CodeDomain> a) {
		username = u;
		password = p;
		Code = a;
	}

	public static CodeDomain getCodeDomain(int i) {
		return Code.get(i);
	}

	public static void clearList(){
		Code.clear();
	}
	
	public static int getSize() {
		return Code.size();
	}

	public static void addCode(CodeDomain code) {
		Code.add(code);
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String pass) {
		password = pass;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String user) {
		username = user;
	}

	public static JSONObject toJson() throws JSONException {
		JSONObject jsonUser = new JSONObject();
		jsonUser.append("username", username);
		jsonUser.append("password", password);
		System.out.println(jsonUser.toString());
		return jsonUser;
	}

	public static void parseString() {

		System.out.println("username:{" + username + "} , password:{"
				+ password + "}");
	}
}
