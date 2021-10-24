package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.*;

import com.qa.base.TestBase;

public class GetAPIPractice extends TestBase {
	String url;
	
	@BeforeTest
	public void getTEstAPI() {
		TestBase base  = new TestBase();
		url = prop.getProperty("URL") + prop.getProperty("ServiceURL");
		
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		CloseableHttpResponse httpresponse = httpclient.execute(httpget);
		
		int respcode = httpresponse.getStatusLine().getStatusCode();
		
		HashMap<String,String> headermap = new HashMap<String,String>();
		
		HttpEntity httpentity = httpresponse.getEntity();
		
		Header h1 = httpentity.getContentType();
		
		System.out.println("The Content Type is :" + h1);
		
		Header[] h = httpresponse.getAllHeaders();
		
		for (Header header: h)
			headermap.put(header.getName(), header.getValue());
		String jsonstring = EntityUtils.toString(httpentity);
		
		System.out.println("The Header Map: " + headermap);
		
		JSONObject Jobj = new JSONObject(jsonstring);
		
		
		
	}
	

}
