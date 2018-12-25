package test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.pojo.Users;

import main.TestBase;

public class PostApiTest extends TestBase {
	
	public TestBase testBase;
	public String serviceURL;
	public String apiURL;
	public String url;
	public RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp()
	{
		testBase=new TestBase();
		serviceURL=prop.getProperty("URL");
		apiURL=prop.getProperty("serviceURL");
		
		url=serviceURL+apiURL;
		System.out.println(url);
		
	}
	
	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException
	{
		restClient=new RestClient();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type","application/json");
		
		
		//Jackson Api(Convert Java Object into Json object)
		ObjectMapper objectMapper=new ObjectMapper();
		Users users=new Users("Morpheus","Leader");
		
		// convert java object intp Json object
		objectMapper.writeValue(new File("/Users/PC/Selenium21/RestApi/src/com/qa/pojo/json/Users.json"), users);
		
		//convert json object to json object string
		String jsonString=objectMapper.writeValueAsString(users);
		System.out.println(jsonString);
		
		closeableHttpResponse=restClient.post(url, jsonString, headerMap);
		
		//1. Get the status code.
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code"+statusCode);
		
	    Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_201,"status is not 200");
	    
	    //2. Json string
	    String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
	    
	    JSONObject responseJSON=new JSONObject(responseString);
	    System.out.println("Response from APi is"+responseJSON);
	    
	    //JSON object to java object.
	    Users userObj=objectMapper.readValue(responseString,Users.class);
	    System.out.println(userObj);
	    Assert.assertEquals(userObj.getName(),users.getName(),"Names are not equal");
	    Assert.assertEquals(userObj.getJob(),users.getJob(),"Jobs are not equal");
	    
	    System.out.println(users);
	    
		
		
		
	}
	

}
