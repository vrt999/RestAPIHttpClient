package com.qa.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
 
 
public class GetRequestJava {
 
    public static void main(String[] args) {
 
        String usernameColonPassword = "user:passwd";
        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());
 
        BufferedReader httpResponseReader = null;
        try {
            // Connect to the web server endpoint
            URL serverUrl = new URL("http://httpbin.org/basic-auth/user/passwd");
            HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
 
            // Set HTTP method as GET
            urlConnection.setRequestMethod("GET");
 
            // Include the HTTP Basic Authentication payload
            urlConnection.addRequestProperty("Authorization", basicAuthPayload);
 
            // Read response from web server, which will trigger HTTP Basic Authentication request to be sent.
            httpResponseReader =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String lineRead;
            while((lineRead = httpResponseReader.readLine()) != null) {
                System.out.println(lineRead);
            }
 
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
 
            if (httpResponseReader != null) {
                try {
                    httpResponseReader.close();
                } catch (IOException ioe) {
                    // Close quietly
                }
            }
        }
 
    }
 
}
