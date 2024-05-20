package day3;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class PathAndQueryParameters {
	//https://reqres.in/api/user?page=2&id=5
	
	@Test
	void testQueryAndPathParameters(){
		
		given()
			.pathParam("myPath", "users")  //path parameters
			.queryParam("page", 2)
			.queryParam("id", 5)
			
		.when()
			.get("https://reqres.in/api/{myPath}")
		
        .then()		
        	.statusCode(200)
        	.log().all();
	}

}
