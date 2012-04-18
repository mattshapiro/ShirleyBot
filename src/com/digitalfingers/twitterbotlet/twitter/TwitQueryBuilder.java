package com.digitalfingers.twitterbotlet.twitter;

import com.digitalfingers.util.StringUtil;

public class TwitQueryBuilder {

	private final static String REQUEST_TYPE = "recent";
	
	private String query = null, since_id = null;
	
	public String buildWithTerm(String query) {
		StringBuffer sb = new StringBuffer();
		if(query != null) { this.query = query; }
		sb.append("http://search.twitter.com/search.json?q="); 
		sb.append(StringUtil.encode(this.query));
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
