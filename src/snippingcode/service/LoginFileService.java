package snippingcode.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginFileService {
	private static File file;
	private static FileWriter fileWriter;
	private static BufferedWriter bufferWriter;
	private static String path = "";
	private static JSONObject userValue;
	private static BufferedReader bufferedReader;
	private static String string;
	private static StringBuilder stringBuilder;

	public static void saveToFile(JSONObject json) throws IOException {
		setPath();
		file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		fileWriter = new FileWriter(path);
		bufferWriter = new BufferedWriter(fileWriter);
		bufferWriter.write(json.toString());
		bufferWriter.close();
	}

	private static void setPath() {
		File tmp = new File("");
		path = tmp.getAbsolutePath() + "/eclipsePlugin.txt";
		System.out.println(path);
	}

	public static JSONObject getFromFile() throws IOException, JSONException {
		setPath();
		bufferedReader = new BufferedReader(new FileReader(path));
		stringBuilder = new StringBuilder();
		string = bufferedReader.readLine();
		while (string != null) {
			stringBuilder.append(string);
			stringBuilder.append(System.lineSeparator());
			string = bufferedReader.readLine();
		}
		string = stringBuilder.toString();
		userValue = new JSONObject(string);
		return userValue;
	}
}
