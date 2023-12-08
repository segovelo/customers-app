package com.segovelo.customers.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/** 
* 8 Dec 2023
* @Javadoc TODO 
*
* @author Segovelo  **/

public class HttpClientUtility {

	
	public static SaveCustomersResponse sendPostRequest(String urlString,String payload){
		SaveCustomersResponse saveCustomersResponse  = new SaveCustomersResponse();
	    try{
	        URL url = new URL(urlString);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type","application/json");
	        connection.setRequestProperty("Accept", "application/json");
	        byte[] out = payload.getBytes(StandardCharsets.UTF_8);
	        OutputStream stream = connection.getOutputStream();
	        stream.write(out);
	        BufferedReader bufferedReader = null;
	        if (100 <= connection.getResponseCode() && connection.getResponseCode() <= 399) {
	        	bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        } else {
	            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String output;
	        while ((output = bufferedReader.readLine()) != null) {
	          sb.append(output);
	        }
	        saveCustomersResponse = new SaveCustomersResponse(connection.getResponseCode(),sb.toString());
	        connection.disconnect();
	    }catch (Exception e){
	        System.out.println(e);
	        System.out.println("The POST request failed");
	    }
	    return saveCustomersResponse;
	}
	
	public static RetrieveCustomerResponse retrieveCustomer(String urlString, String customerRef) {
		RetrieveCustomerResponse retrieveCustomerResponse = new RetrieveCustomerResponse();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("customerRef", customerRef);
		try {
			URL url = new URL(getParamsString(urlString, queryParams));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");			
	        connection.setRequestProperty("Accept", "application/json");
	        connection.setConnectTimeout(5000);
	        connection.setReadTimeout(5000);
			int status = connection.getResponseCode();
			Reader streamReader = null;
			if (status > 299) {
			    streamReader = new InputStreamReader(connection.getErrorStream());
			} else {
			    streamReader = new InputStreamReader(connection.getInputStream());
			}
			BufferedReader in = new BufferedReader(streamReader);
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
			String contentStr = content.toString();
			int index =  contentStr.indexOf("\"customer\":");
			String customerStr = contentStr.substring(index+11);
			Customer customer = null;
			if (status < 300 && index > -1) {
				customer = JsonUtility.jsonToCustomer(customerStr);
			}
			retrieveCustomerResponse = new RetrieveCustomerResponse(status, contentStr, customer);
			connection.disconnect();
	    }catch (Exception e){
	        System.out.println(e);
	        System.out.println("The GET request failed");
	    }
		return retrieveCustomerResponse;
	}
	
    public static String getParamsString(String url, Map<String, String> queryParams) 
    	      throws UnsupportedEncodingException{
    	        StringBuilder result = new StringBuilder();
                result.append(url);
                result.append("?");
    	        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
    	          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
    	          result.append("=");
    	          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
    	          result.append("&");
    	        }

    	        String resultString = result.toString();
    	        return resultString.length() > 0
    	          ? resultString.substring(0, resultString.length() - 1)
    	          : resultString;
    	    }

}
