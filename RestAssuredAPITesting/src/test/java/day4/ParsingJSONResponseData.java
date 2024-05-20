package day4;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class ParsingJSONResponseData {
	
	//@Test(priority=1)
	void testJSONResponse()
	{
		//Approach1
		
		/*given()
			.contentType(ContentType.Json)
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
		 	.statusCode(200)
		 	//.header("Content-Type", "application/json; charset=utf-8")
		 	.body("book[3].title",equalTo("The Lord of the Rings"));*/
		
		//Approach2
		
		
		Response res = given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName,"The Lord of the Rings" );
		
	}
	
	@Test(priority=2)
	void testJSONResponseBodyData()
	{
		
		Response res = given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/store");
		
		/*Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName,"The Lord of the Rings" );*/
		
		//JSONObject class
		
		JSONObject jo =new JSONObject(res.asString()); //converting response to JSONObject Type
		
		boolean status=false;
		
		for(int i= 0;i<jo.getJSONArray("book").length();i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			//System.out.println(bookTitle);
			if(bookTitle.equals("The Lord of the Rings")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		
	}

}
