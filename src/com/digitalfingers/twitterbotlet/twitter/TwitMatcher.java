package com.digitalfingers.twitterbotlet.twitter;

public class TwitMatcher {

	private final static String [] MATCHES = {
		"surely you can't be serious",
		"surely you can not be serious",
		"surely you're not serious",
		"surely you are not serious"
	};
	
	public boolean isMatch(Tweet tweet) {
		String message = tweet.message.toLowerCase();
		//if(tweet.user != "typorrhea") return false;
		for(String compare : MATCHES) {
			if(message.contains(compare)) {
				return true;
			}
		}
		return false;
	}

}
