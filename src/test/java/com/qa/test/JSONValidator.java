package com.qa.test;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONValidator {

	public static String getValueByJPath(JSONObject responsejson,String jpath) {
		Object obj = responsejson;
		for(String s: jpath.split("/")) 
			if(!(s.isEmpty()))
				if(!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if(s.contains("[") || s.contains("]"))
					obj = ((JSONArray)((JSONObject) obj).get(s.split("\\[")[0]))
					.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		
			return obj.toString();
			
			
		
	}
	
	public static void main(String[] args) {
		String jPath = "/[per_page]";
		
		String jsonresponse = "{\"page\":2,\"per_page\":6,\"total\":12,\"total_pages\":2,\"data\":[{\"id\":7,\"email\":\"michael.lawson@reqres.in\",\"first_name\":\"Michael\",\"last_name\":\"Lawson\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg\"},{\"id\":8,\"email\":\"lindsay.ferguson@reqres.in\",\"first_name\":\"Lindsay\",\"last_name\":\"Ferguson\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/araa3185/128.jpg\"},{\"id\":9,\"email\":\"tobias.funke@reqres.in\",\"first_name\":\"Tobias\",\"last_name\":\"Funke\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/vivekprvr/128.jpg\"},{\"id\":10,\"email\":\"byron.fields@reqres.in\",\"first_name\":\"Byron\",\"last_name\":\"Fields\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/russoedu/128.jpg\"},{\"id\":11,\"email\":\"george.edwards@reqres.in\",\"first_name\":\"George\",\"last_name\":\"Edwards\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/mrmoiree/128.jpg\"},{\"id\":12,\"email\":\"rachel.howell@reqres.in\",\"first_name\":\"Rachel\",\"last_name\":\"Howell\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg\"}]}";
		
		JSONObject jobj = new JSONObject(jsonresponse);
		
		JSONArray jarr = jobj.getJSONArray("data");
		System.out.println("Data array length: " + jarr.length());
		
		Object email  = jarr.getJSONObject(0).get("email");
//		JSONValidator jsonvalidator = new JSONValidator();
		
		String output = getValueByJPath(jobj, jPath);
		
		System.out.println(" The Array email is : " + email);
		
		System.out.println(" The output is : " + output);

	}

	
}



