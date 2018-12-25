package test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.util.TestUtil;

import main.TestBase;

public class GetAPITest extends TestBase {
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
	
	@Test(priority=1)
	public void getTestWithOutHeader() throws ClientProtocolException, IOException
	{
		restClient=new RestClient();
		closeableHttpResponse=restClient.get(url);
		
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code"+statusCode);
		
	    Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"status is not 200");
		
	    //2. It returns JSON String 
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject jsonObject=new JSONObject(responseString);
		System.out.println("Response JSON"+jsonObject);
		
		//3. Get the per page value
		String perPageValue=TestUtil.getValueByJPath(jsonObject,"/per_page");
		System.out.println("per page value"+perPageValue);
		
		Assert.assertEquals(perPageValue,"3");
		
		//4. Get the total value
		String totalValue=TestUtil.getValueByJPath(jsonObject,"/total");
		System.out.println("totalValue"+totalValue);
		
		Assert.assertEquals(totalValue,"12");
		
		//5. Get the JSON array value
		String lastName=TestUtil.getValueByJPath(jsonObject,"/data[0]/last_name");
		String id=TestUtil.getValueByJPath(jsonObject,"/data[0]/id");
		String avatar=TestUtil.getValueByJPath(jsonObject,"/data[0]/avatar");
		String firstName=TestUtil.getValueByJPath(jsonObject,"/data[0]/first_name");
		System.out.println("lastName "+totalValue);
		System.out.println("id "+id);
		System.out.println("avatar "+avatar);
		System.out.println("firstName "+firstName);
		
		
		
		Header[] headerArray=closeableHttpResponse.getAllHeaders();
		HashMap<String,String> map=new HashMap<String,String>();
		
		for(Header headers : headerArray)
		{
			map.put(headers.getName(), headers.getValue());
		}
		
		System.out.println("Headers"+map);

		
	}

	@Test(priority=2)
	public void getTestWithHeaders() throws ClientProtocolException, IOException
	{
		restClient=new RestClient();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type","application/json");
		//headerMap.put("Username","application@gmail.com");
		//headerMap.put("Password","password");
		//headerMap.put("Auth Token","2554%@#%$vgh");
		
		
		closeableHttpResponse=restClient.get(url,headerMap);
		
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code"+statusCode);
		
	    Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"status is not 200");
		
	    //2. It returns JSON String 
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject jsonObject=new JSONObject(responseString);
		System.out.println("Response JSON"+jsonObject);
		
		//3. Get the per page value
		String perPageValue=TestUtil.getValueByJPath(jsonObject,"/per_page");
		System.out.println("per page value"+perPageValue);
		
		Assert.assertEquals(perPageValue,"3");
		
		//4. Get the total value
		String totalValue=TestUtil.getValueByJPath(jsonObject,"/total");
		System.out.println("totalValue"+totalValue);
		
		Assert.assertEquals(totalValue,"12");
		
		//5. Get the JSON array value
		String lastName=TestUtil.getValueByJPath(jsonObject,"/data[0]/last_name");
		String id=TestUtil.getValueByJPath(jsonObject,"/data[0]/id");
		String avatar=TestUtil.getValueByJPath(jsonObject,"/data[0]/avatar");
		String firstName=TestUtil.getValueByJPath(jsonObject,"/data[0]/first_name");
		System.out.println("lastName "+totalValue);
		System.out.println("id "+id);
		System.out.println("avatar "+avatar);
		System.out.println("firstName "+firstName);
		
		
		
		Header[] headerArray=closeableHttpResponse.getAllHeaders();
		HashMap<String,String> map=new HashMap<String,String>();
		
		for(Header headers : headerArray)
		{
			map.put(headers.getName(), headers.getValue());
		}
		
		System.out.println("Headers"+map);

		
	}

	
	
}
