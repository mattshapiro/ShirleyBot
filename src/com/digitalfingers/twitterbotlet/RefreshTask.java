package com.digitalfingers.twitterbotlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.digitalfingers.twitterbotlet.twitter.Tweet;
import com.digitalfingers.twitterbotlet.twitter.TwitMatcher;
import com.digitalfingers.twitterbotlet.twitter.TwitQueryBuilder;
import com.digitalfingers.twitterbotlet.twitter.TwitSearchResponse;
import com.digitalfingers.util.HttpUtil;
import com.digitalfingers.util.OAuthUtil;
import com.digitalfingers.util.StringUtil;

public class RefreshTask extends TimerTask {
	
		@Override
		public void run() {
			// search for query
			TwitQueryBuilder queryBuilder = new TwitQueryBuilder();
			String url = queryBuilder.buildWithTerm("surely");
			String responseStr = HttpUtil.getResponseAsString(url);
			
			TwitSearchResponse response = parseJSONString(responseStr);
			TwitMatcher matcher = new TwitMatcher();
			
			for(Tweet tweet : response.getTweets()) {
				if(matcher.isMatch(tweet)) {
					sendTweet(tweet);
				}
			}
			
			sendTweet(response.getTweets().get(0));
		}
		
		private void sendTweet(Tweet tweet) {
			StringBuffer status = new StringBuffer();
			
			status.append("@").append(tweet.user).append(" ");
			status.append("I am serious.  And don't call me Shirley.");
		
			HashMap <String,Object> map = new HashMap<String,Object>();
			String url = createUpdateURL(tweet, status.toString());
			String authorization = OAuthUtil.getOAuthHeaderString(status.toString(), tweet.id, url);
			map.put("Content-Type", "application/x-www-form-urlencoded");
			map.put("Authorization", authorization);
			try {
				url = createUpdateURL(tweet, URLEncoder.encode(status.toString(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String response = HttpUtil.postResponseAsString(url, map, null);
			System.out.println(response);
		}

		private String createUpdateURL(Tweet tweet, String stat) {
			StringBuffer url = new StringBuffer();
			url.append("https://api.twitter.com/statuses/update.json?");
			url.append("in_reply_to_status_id=");
			url.append(tweet.id);
			url.append("&status=");
			url.append(stat);
			return url.toString();
		}

		private TwitSearchResponse parseJSONString(String toParse) {
			JSONObject json = (JSONObject) JSONSerializer.toJSON(toParse);   
			JSONArray results = json.getJSONArray("results");
			TwitSearchResponse response = new TwitSearchResponse();
			int size = results.size();
			for(int i = 0; i < size; i++) {
				JSONObject result = results.getJSONObject(i);
				Tweet tweet = new Tweet();
				tweet.user = result.getString("from_user");
				tweet.id = result.getString("id_str");
				tweet.message = result.getString("text");
				response.addTweet(tweet);
			}
			return response;
		}
}