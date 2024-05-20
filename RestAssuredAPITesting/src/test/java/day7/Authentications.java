package day7;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	@Test(priority=1)
	void testBasicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200);
	}
	
	@Test(priority=2)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200);
	}
	
	@Test(priority=3)
	void testPreemptiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200);
	}
	
	@Test(priority=4)
	void testBearerTokenAuthentication() {
		
		String bearerToken="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";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test ----OAuth 1.0 Authentication
	void testOAuth1Authentication() {
		
		given()
			.auth().oauth("consumerKey","consumerSecrat","accessToken","tokenSecrate")
		.when()
			.get("url")
		.then()
			.statusCode(200);
		
	}
	
	@Test(priority=5) //----OAuth 2.0 Authentication
		void testOAuth2Authentication() {
			
			given()
				.auth().oauth2("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")
			.when()
				.get("https://api.github.com/user/repos")
			.then()
				.statusCode(200);
			
		}
	
	@Test(priority=6)
	void testAPIKeyAuthentication() {
		
		given()
			.queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecasr/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	

}
