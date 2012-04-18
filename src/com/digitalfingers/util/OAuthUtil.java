package com.digitalfingers.util;

import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class OAuthUtil {

	private final static String OAUTH_CONSUMER_KEY = "",
								OAUTH_SIGNATURE_METHOD = "HMAC-SHA1",
								OAUTH_TOKEN = "",
								OAUTH_VERSION = "1.0",
								CONSUMER_SECRET = "",
								TOKEN_SECRET = "";
	
	public static String getOAuthHeaderString(String status, String reply_id, String url) {
		final String [][] paramTable = 
			{
				{"in_reply_to_status_id", reply_id},
				{"oauth_consumer_key", OAUTH_CONSUMER_KEY},
				{"outh_nonce", UUID.randomUUID().toString()},
				{"oauth_signature_method", OAUTH_SIGNATURE_METHOD},
				{"oauth_timestamp", Long.toString((new Date()).getTime())},
				{"oauth_token", OAUTH_TOKEN},
				{"oauth_version", OAUTH_VERSION},
				{"status", status}
			};
		
		StringBuffer token = new StringBuffer(),
					 paramString = new StringBuffer(),
					 signingString = new StringBuffer(),
					 headerString = new StringBuffer();
		
		int idx_key = 1,
			idx_sig_method = 3,
			idx_nonce = 2, 
			idx_timestamp = 4,
			idx_token = 5,
			idx_version = 6;
		
		token.append("POST&").append(encode(url));
		
		// build and add parameter string
		for(int i = 0; i < paramTable.length; i++) {
			paramString.append("&");
			for(int j = 0; j < 2; j++) {
				paramString.append(encode(paramTable[i][j]));
			}
		}
		
		token.append(encode(paramString.toString()));
		
		// build signing key
		signingString.append(encode(CONSUMER_SECRET)).append("&").append(encode(TOKEN_SECRET));
		
		// hash
		String result = null;
		try {
		    Mac mac = Mac.getInstance("HmacSHA1");
		    SecretKeySpec secret = new SecretKeySpec(signingString.toString().getBytes(),"HmacSHA1");
		    mac.init(secret);
		    byte[] digest = mac.doFinal(token.toString().getBytes());
		    result = new String(digest);
		    System.out.println(result);  
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}

		// build the header string
		
		headerString.append(encode(paramTable[idx_key][0])).append("=")
					.append(wrap(paramTable[idx_key][1]));
		headerString.append(", ");
		headerString.append(encode(paramTable[idx_nonce][0])).append("=")
					.append(wrap(paramTable[idx_nonce][1]));
		headerString.append(", ");
		headerString.append(encode("oauth_signature")).append("=")
					.append(wrap(result));
		headerString.append(", ");
		headerString.append(encode(paramTable[idx_sig_method][0])).append("=")
					.append(wrap(paramTable[idx_sig_method][1]));
		headerString.append(", ");
		headerString.append(encode(paramTable[idx_timestamp][0])).append("=")
					.append(wrap(paramTable[idx_timestamp][1]));
		headerString.append(", ");
		headerString.append(encode(paramTable[idx_token][0])).append("=")
					.append(wrap(paramTable[idx_token][1]));
		headerString.append(", ");
		headerString.append(encode(paramTable[idx_version][0])).append("=")
					.append(wrap(paramTable[idx_version][1]));
		
		return headerString.toString();
	}
	
	private static String wrap(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append("\"").append(encode(str)).append("\"");
		return sb.toString();
	}
	
	private static String encode(String str) {
		String result = null;
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			
		}
		return result;
	}
	
}
