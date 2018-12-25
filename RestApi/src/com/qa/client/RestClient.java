package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	//.1 Get Method without headers
	public CloseableHttpResponse get(String URL) throws ClientProtocolException, IOException
	{
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
		HttpGet httpGet= new HttpGet(URL);
		CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);
		return closeableHttpResponse;
					
	}
	
	//2. Get Method with headers
	public CloseableHttpResponse get(String URL,HashMap<String,String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
		HttpGet httpGet= new HttpGet(URL);
		
		for(Map.Entry<String,String> entry :headerMap.entrySet())
		{
			httpGet.addHeader(entry.getKey(),entry.getValue());
			
		}
		CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);
		return closeableHttpResponse;
					
	}
	
	//3.Post method for headers
	public CloseableHttpResponse post(String URL,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException, IOException
    {
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
		HttpPost httpPost= new HttpPost(URL); //http post request
		httpPost.setEntity(new StringEntity(entityString)); //for payload
		
		for(Map.Entry<String,String> entry :headerMap.entrySet())
		{
			httpPost.addHeader(entry.getKey(),entry.getValue());
			
		}
	
	
		CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpPost);
		return closeableHttpResponse;
    }

	
}
