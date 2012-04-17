package com.digitalfingers.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpUtil {

	public static String getResponseAsString(String url) {
		// pretty much ripped from the Apache example 
		// http://hc.apache.org/httpcomponents-client-ga/httpclient/examples/org/apache/http/examples/client/ClientWithResponseHandler.java
		HttpClient httpclient = new DefaultHttpClient();
		String responseBody = null;
		try {
            HttpGet httpget = new HttpGet(url);

            System.out.println("executing request " + httpget.getURI());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println("----------------------------------------");

        } catch (ClientProtocolException e) {
			System.out.println("ClientProtocolException " + e.getMessage());
        	e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException " + e.getMessage());
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
		
		return responseBody;
	}
	
}
