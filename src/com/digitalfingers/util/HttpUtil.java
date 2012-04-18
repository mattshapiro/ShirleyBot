package com.digitalfingers.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpUtil {

	public static String getResponseAsString(String url) {
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
	
	public static String postResponseAsString(String url, HashMap <String,Object> attributes, String params) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		Iterator iterator = attributes.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		HttpResponse response;
		while(iterator.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>)iterator.next();
			httppost.addHeader(entry.getKey(), (String)entry.getValue());
		}
		if(params!=null) {
			InputStreamEntity is = new InputStreamEntity(new ByteArrayInputStream(params.getBytes()),-1);
			httppost.setEntity(is);
		}
		try {
			response = httpclient.execute(httppost);
			BufferedReader bis = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			while((line = bis.readLine()) != null) {
				sb.append(line);
				System.out.println(line);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
