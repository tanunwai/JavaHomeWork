package com.demo.javahomework.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class ParseFromJson {

	private static final String USER_AGENT = "MozilMozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";

	// private static final String
	// urlStr="http://api.coindesk.com/v1/bpi/currentprice.json";
	/*
	 * private static String
	 * PATH="{\"time\":{\"updated\":\"Apr 8, 2022 08:52:00 UTC\",\r\n" +
	 * "		 \"updatedISO\":\"2022-04-08T08:52:00+00:00\",\r\n" +
	 * "		 \"updateduk\":\"Apr 8, 2022 at 09:52 BST\"},\r\n" +
	 * " \"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\r\n"
	 * + " \"chartName\":\"Bitcoin\",\r\n" +
	 * " \"bpi\":{\"USD\":{\"code\":\"USD\",\r\n" +
	 * "			   \"symbol\":\"&#36;\",\r\n" +
	 * "			   \"rate\":\"43,875.1231\",\r\n" +
	 * "			   \"description\":\"United States Dollar\",\r\n" +
	 * "			   \"rate_float\":43875.1231},\r\n" +
	 * "		\"GBP\":{\"code\":\"GBP\",\r\n" +
	 * "			   \"symbol\":\"&pound;\",\r\n" +
	 * "			   \"rate\":\"33,642.5230\",\r\n" +
	 * "			   \"description\":\"British Pound Sterling\",\r\n" +
	 * "			   \"rate_float\":33642.523},\r\n" +
	 * "	    \"EUR\":{\"code\":\"EUR\",\r\n" +
	 * "			   \"symbol\":\"&euro;\",\r\n" +
	 * "			   \"rate\":\"40,373.5372\",\r\n" +
	 * "			   \"description\":\"Euro\",\r\n" +
	 * "			   \"rate_float\":40373.5372}\r\n" + "		}\r\n" + "}";
	 */

	public String queryFromJsoin(String url) {
		String jsonCoinPrice = null;
		StringBuilder builder = new StringBuilder(url);

		try {
			URL urlOb = new URL(builder.toString());
			HttpURLConnection con = (HttpURLConnection) urlOb.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Angent", USER_AGENT);
			con.setRequestProperty("Accept-Charset", "UTF-8");
			if (con.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				jsonCoinPrice=sb.toString();
			}
			
		} catch (Exception e) {
			jsonCoinPrice=e.getMessage();
		}
		return jsonCoinPrice;
	}
}