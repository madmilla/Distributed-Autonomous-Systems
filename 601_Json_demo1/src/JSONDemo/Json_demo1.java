package JSONDemo;

import org.json.*;

class Json_demo1 {
	public void run() throws Exception {
		JSONArray ja1 = new JSONArray();
		ja1.put(1);
		ja1.put("string");
		System.out.println(ja1);

		JSONArray ja2 = new JSONArray(new int[] { 9, 8, 7 });
		System.out.println(ja2);

		JSONObject jo1 = new JSONObject();
		jo1.put("Engels", 7);
		jo1.put("wiskunde", 8);
		System.out.println(jo1);

		JSONObject jo2 = new JSONObject();
		jo2.put("cijferlijst", jo1);
		jo2.put("objecten", ja1);
		System.out.println(jo2);

		JSONArray ja3 = new JSONArray();
		ja3.put(jo1);
		ja3.put(jo2);
		System.out.println(ja2);

		JSONObject jo3 = new JSONObject();
		jo3.put("vb", 2);
		jo3.put("vb", 3); // overschrijving
		System.out.println(jo3);

		// JSON -> String
		String s1 = ja2.toString();
		System.out.println(s1);
		String s2 = jo2.toString();
		System.out.println(s2);

		// String -> JSON
		ja2 = new JSONArray(s1);
		System.out.println(ja2);
		jo2 = new JSONObject(s2);
		System.out.println(jo2);

	}

	public static void main(String[] arg) throws Exception {
		Json_demo1 jd = new Json_demo1();
		jd.run();
	}
}