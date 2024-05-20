package day3;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	
	@Test(priority=1)
	void testCookies()
	{
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC","AQTF6Hxp9nfk9KB6R0TZ95qH_wxz-KQeuoz8I4dV2wQLiLXWOqDl61bujw")
			//Cookie value will change time to time so if we have to check value then we have to
			//consider that this validation is going to fail
			.log().all();
	}
	
	@Test(priority=2)
	void getCookiesInfo()
	{
		
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
		//String cookieValue=res.getCookie("AEC");
		//System.out.println("Value of Cookie is : "+cookieValue);
		
		//get all cookies info
		Map<String,String>cookiesValue=res.getCookies();
		System.out.println(cookiesValue.keySet());
		
		for(String k:cookiesValue.keySet()) {
			System.out.println(k+" : "+res.getCookie(k));
		}
	}
}
