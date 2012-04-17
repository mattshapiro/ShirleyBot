package com.digitalfingers.twitterbotlet;

import java.util.TimerTask;

import com.digitalfingers.twitterbotlet.twitter.TwitQueryBuilder;
import com.digitalfingers.util.HttpUtil;

public class RefreshTask extends TimerTask {

		@Override
		public void run() {
			// search for query
			TwitQueryBuilder queryBuilder = new TwitQueryBuilder();
			String url = queryBuilder.buildWithTerm("surely");
			String response = HttpUtil.getResponseAsString(url);
			// validate oauth
			// loop n query matches
				// parse match
				// construct response
				// update with @reply
		}
}