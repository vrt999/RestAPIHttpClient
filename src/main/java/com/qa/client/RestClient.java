package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class RestClient {
	
	public CloseableHttpResponse get(String url) throws Exception {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();		
		
		HttpGet httpget = new HttpGet(url);		
		
		CloseableHttpResponse httpresponse = httpclient.execute(httpget);		
		
		return httpresponse;
	}
	
	public CloseableHttpResponse get(String url,HashMap<String,String> headermap) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpget = new HttpGet(url);
		
		for(Map.Entry<String, String> entry : headermap.entrySet())
			httpget.addHeader(entry.getKey(), entry.getValue());		
		
		CloseableHttpResponse httpresponse = httpclient.execute(httpget);
		
		return httpresponse;
	}
	
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headermap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpPost httppost = new HttpPost(url);//post request
		
		for(Map.Entry<String,String> entry : headermap.entrySet())// for headers
		httppost.addHeader(entry.getKey(), entry.getValue());
		
//		List<NameValuePair> urlParameters = new ArrayList<>();
				
		httppost.setEntity(new StringEntity(entityString));// for request payload
		
		CloseableHttpResponse httpresponse = httpclient.execute(httppost);
		
		return httpresponse;
		
		
	}

}
