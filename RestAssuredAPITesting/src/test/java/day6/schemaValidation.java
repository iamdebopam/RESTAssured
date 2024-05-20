package day6;

import org.testng.annotations.*;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class schemaValidation {
	
	@Test
	void jsonschemavalidatin() {
		
		given()
		
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
		//XML Schema Validation
		//	.assertThat().body()(ResrAssuredMatchers.matchesXsdInClasspath("store.xsd"));
	}

}
