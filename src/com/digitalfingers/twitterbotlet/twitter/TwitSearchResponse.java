package com.digitalfingers.twitterbotlet.twitter;

import java.util.ArrayList;
import java.util.List;

public class TwitSearchResponse {
	private List <Tweet> tweets;
	
	public void addTweet(Tweet tweet) {
		if(tweets == null) {
			tweets = new ArrayList<Tweet>();
		}
		tweets.add(tweet);
	}
	
	public List <Tweet> getTweets() {
		return tweets;
	}
}
