package com.digitalfingers.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringUtil {
	public static String encode(String in) {
		String result = null;
		try {
			result = URLEncoder.encode(in, "UTF-8");
			// TODO get a better "percent encoding" lib
			result = result.replace("+", "%20");
			result = result.replace(".", "%2E");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
