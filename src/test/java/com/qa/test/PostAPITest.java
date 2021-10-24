package com.qa.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import java.io.File;
import java.util.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class PostAPITest extends TestBase {
	
	TestBase testbase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient = new RestClient();
	CloseableHttpResponse httpresponse;
	HashMap<String,String> headerMap;
	
	@BeforeMethod
	public void setUp() {
		testbase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("ServiceURL");
		url = serviceUrl + apiUrl;
	}
	
	@Test
	public void postTest() throws Exception {
		
//		RestClient restClient = new RestClient();
		
		headerMap = new HashMap<String,String>();
		headerMap.put("content-type","application/json");
		
		//jackson API
		ObjectMapper  mapper = new ObjectMapper();		
		Users users = new Users("morpheus","leader");
		
		//object to json file
		mapper.writeValue(new File("F:\\myproject\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		
		//Object to JSON in String
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println("The usersJsonString is : " + usersJsonString);
		
		httpresponse  = restClient.post(url, usersJsonString, headerMap);
		
		//Get and validate status code
		int statuscode  = httpresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, 201);
		
		//JSONString
		String responseString = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		JSONObject responsejson = new JSONObject(responseString);
		
		System.out.println("The ResponseJSON is : " + responsejson);
		
		Users usersRespObj  = mapper.readValue(responseString, Users.class);
		
		Assert.assertEquals(usersRespObj.getJob(), users.getJob(), "Job is not Equal");
		Assert.assertEquals(usersRespObj.getName(), users.getName(), "Name is not Equal");
		
		
	}

}
