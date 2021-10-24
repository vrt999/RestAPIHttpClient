package com.qa.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.util.TestUtil;

import java.io.File;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class GetAPITest extends TestBase {
	
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
	//without headers
	@Test(priority = 1)
	public void getTestWithOutHeaders() throws Exception {
		
		httpresponse = restClient.get(url);
		
		//get response status code
		int statuscode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code received: " + statuscode); 
		
		//get response status code
		String reasonPhrase = httpresponse.getStatusLine().getReasonPhrase();
		System.out.println("The reason phrase received: " + reasonPhrase); 

		//get entity for httpresponse
		String responseString = EntityUtils.toString(httpresponse.getEntity());
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response JSON received: " + responseJson);
		
		
		//get Headers from the response
		Header[] headerArray = httpresponse.getAllHeaders();
		HashMap<String,String> headerMap = new HashMap<String,String>();
		
		for(Header header: headerArray) {
			headerMap.put(header.getName(), header.getValue());
		}
		System.out.println("All the headers received: " + headerMap);
		
		String jpath1 = "/per_page";
		
		//Validate JSON response for single element
		String per_page = TestUtil.getValueByJPath(responseJson, jpath1);		
		Assert.assertEquals(Integer.parseInt(per_page), 6, "per_page isnot equal");
		
		//validate JSON response for JOSON Array
		String jpath2 = "/data[0]/first_name";		
		String first_name = TestUtil.getValueByJPath(responseJson, jpath2);
		Assert.assertEquals(first_name, "Michael","first_name isnot equal");
		
	}
	
	@Test(priority = 2)
	public void getTestWithHeaders() throws Exception {
		
		headerMap = new HashMap<String,String>();
		headerMap.put("content-type","application/json");
		
		httpresponse = restClient.get(url, headerMap);
		
		//get response status code
		int statuscode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code received: " + statuscode); 

		//get entity for httpresponse
		String responseString = EntityUtils.toString(httpresponse.getEntity());
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response JSON received: " + responseJson);
		
		
		//get Headers from the response
		Header[] headerArray = httpresponse.getAllHeaders();
		HashMap<String,String> outputheaderMap = new HashMap<String,String>();
		
		for(Header header: headerArray) {
			outputheaderMap.put(header.getName(), header.getValue());
		}
		System.out.println("All the headers received: " + outputheaderMap);
		
		String jpath1 = "/per_page";
		
		//Validate JSON response for single element
		String per_page = TestUtil.getValueByJPath(responseJson, jpath1);		
		Assert.assertEquals(Integer.parseInt(per_page), 6, "per_page isnot equal");
		
		//validate JSON response for JOSON Array
		String jpath2 = "/data[0]/first_name";		
		String first_name = TestUtil.getValueByJPath(responseJson, jpath2);
		Assert.assertEquals(first_name, "Michael","first_name isnot equal");		
		
	}

}
