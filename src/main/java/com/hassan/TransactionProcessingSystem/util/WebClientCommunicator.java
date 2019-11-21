package com.hassan.TransactionProcessingSystem.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WebClientCommunicator {
	
	public static String getRequest(String params) throws IOException {
		URL url = new URL("https://api.exchangeratesapi.io/latest?" + params);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String json = "";
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();
		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext())
			{
				json += sc.nextLine();
			}
			sc.close();
		}
		
		return json;
	}

}
