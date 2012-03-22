package com.digitalfingers.twitterbotlet.twitter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TwitQueryBuilder {

	private final static String REQUEST_TYPE = "recent";
	
	private String query = null, since_id = null;
	
	public String buildWithTerm(String query) {
		StringBuffer sb = new StringBuffer();
		if(query != null) { this.query = query; }
		sb.append("http://search.twitter.com/search.json?q="); 
		try { sb.append(URLEncoder.encode(this.query,"UTF-8")); } catch (UnsupportedEncodingException e) {}
		sb.append("&rpp=100&include_entities=true&result_type=recent");
		if(since_id != null) {
			sb.append("&since_id=");
			sb.append(since_id);
		}
		return sb.toString();
	}
	
	public void setSince(String since_id) {
		this.since_id = since_id;
	}

}
