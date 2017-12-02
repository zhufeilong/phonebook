package hackajob.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import hackajob.phonebook.model.Contact;

public class Utils {
	public static String getFromHttp(URL url) throws IOException {
		URLConnection con = url.openConnection();
		try( InputStream inputStream =con.getInputStream();){
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
			    result.write(buffer, 0, length);
			}
			return result.toString(StandardCharsets.UTF_8.name());
		}
	}

	public static List<Contact> getContact(String content) {
		List<Contact> list = new ArrayList<>();
		if(content==null||content.length()==0) {
			return list;
		}
		JSONObject obj = new JSONObject(content);
		JSONArray array = (JSONArray) obj.get("contacts");
		for(int i = 0;i<array.length();i++) {
			Map<String,Object> term = (Map<String,Object>) ((JSONObject)array.get(i)).toMap();
			list.add(new Contact(term));
		}
		return list;
	}
}
