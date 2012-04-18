package com.digitalfingers.twitterbotlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.digitalfingers.twitterbotlet.twitter.Tweet;
import com.digitalfingers.twitterbotlet.twitter.TwitQueryBuilder;
import com.digitalfingers.twitterbotlet.twitter.TwitSearchResponse;
import com.digitalfingers.util.HttpUtil;

public class RefreshTask extends TimerTask {

		@Override
		public void run() {
			// search for query
			TwitQueryBuilder queryBuilder = new TwitQueryBuilder();
			String url = queryBuilder.buildWithTerm("surely");
			String responseStr = HttpUtil.getResponseAsString(url);
			
			parseJSONString(responseStr);
			
//	        double coolness = json.getDouble( "coolness" );
//	        int altitude = json.getInt( "altitude" );
//	        JSONObject pilot = json.getJSONObject("pilot");
//	        String firstName = pilot.getString("firstName");
//	        String lastName = pilot.getString("lastName");
	        
//	        System.out.println( "Coolness: " + coolness );
//	        System.out.println( "Altitude: " + altitude );
//	        System.out.println( "Pilot: " + lastName );

			// parse responses
			// validate oauth
			// loop n query matches
				// parse match
				// construct response
				// update with @reply
		}
		
		public TwitSearchResponse parseJSONString(String toParse) {
			JSONObject json = (JSONObject) JSONSerializer.toJSON(toParse);   
			JSONArray results = json.getJSONArray("results");
			TwitSearchResponse response = new TwitSearchResponse();
			int size = results.size();
			for(int i = 0; i < size; i++) {
				JSONObject result = results.getJSONObject(i);
				Tweet tweet = new Tweet();
				tweet.user = result.getString("from_user");
				tweet.message = result.getString("text");
				response.addTweet(tweet);
			}
			return response;
		}
}