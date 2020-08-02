package com.service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
 
public class sendSMS {
	/*public static void main(String[] args) {
		String message=sendSms();
		System.out.println("message:"+message);
	}*/
	public static void sendSms(String phonenumber) {
		try {
			// Construct data
			String apiKey = "apikey=" + "lE3wUytNNGM-w3PIINyPigfiRPKupXp1UN3RtOsAG1";
			String message = "&message=" + "Hi , THis Mahesh , test message.";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + phonenumber;
			System.out.println( apiKey + numbers + message + sender);
			  // Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			System.out.println("Message Status:"+stringBuffer.toString());
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			e.printStackTrace();
			//return "Error "+e;
		}
	}
}