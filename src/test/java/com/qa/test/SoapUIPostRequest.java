package com.qa.test;

import java.io.File;
import java.io.FileInputStream;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;

public class SoapUIPostRequest {
	
	@Test
	public void soapGetRequest() throws Exception {
		//wsdl file: http://currencyconverter.kowabunga.net/converter.asmx?wsdl
		
		File soapRequestFile = new File(".\\SOAP Requests\\Sample Request1.xml");
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httppost = new HttpPost("http://currencyconverter.kowabunga.net/converter.asmx");
		
		httppost.addHeader("Content-Type","text/xml");
		httppost.setEntity(new InputStreamEntity(new FileInputStream(soapRequestFile)));
		
		CloseableHttpResponse httpresponse = httpClient.execute(httppost);
		
		int statuscode = httpresponse.getStatusLine().getStatusCode();
		
		System.out.println("The StatusCode : " + statuscode);
		
		Assert.assertEquals(statuscode, 200);
		
		String responseString = EntityUtils.toString(httpresponse.getEntity());
		
//		System.out.println("The Response String: " + responseString);
		
		XmlPath xmlpath = new XmlPath(responseString);
		
		String rates = xmlpath.getString("GetConversionRateResult");
		
		Assert.assertEquals(rates, 75.01513167814258562343086588);
		
		System.out.println("The converted INR value: " + rates);		
		
	}

}
