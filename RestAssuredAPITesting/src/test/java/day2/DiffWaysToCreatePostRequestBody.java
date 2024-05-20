package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
 * Different ways to create POST request body
 * 1) Post request body using HashMap
 * 2) Post request body creation using Org.JSON
 * 3) Post request body creation using POJO Class
 * 4) Post request using external json file data
 */

public class DiffWaysToCreatePostRequestBody {
	
	//1) Post request body using HashMap
	//@Test(priority=1)
	void testPostusingHshMap() {
		
		HashMap data=new HashMap();
		data.put("name", "Pratima");
		data.put("location", "France");
		data.put("phone","123456");
		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Pratima"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			//.header("Content-Type","application/json; charset=uft-8")
			.log().all();
		
	}
	
	//2) Post Request body using Org.Json Library
		//@Test(priority=1)
		void testPostusingJsonLibrary() {
			
			JSONObject data=new JSONObject();
			
			data.put("name", "Pratima");
			data.put("location", "France");
			data.put("phone", "123456");
			data.put("name", "Pratima");
			
			String courseArr[]= {"C","C++"};
			data.put("courses", courseArr);
			
			given()
				.contentType("application/json")
				.body(data.toString())              //ContentType should be in json format but body should 
													// be in String Format									
			
			.when()
				.post("http://localhost:3000/students")
				
			.then()
				.statusCode(201)
				.body("name",equalTo("Pratima"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("123456"))
				.body("courses[0]",equalTo("C"))
				.body("courses[1]",equalTo("C++"))
				//.header("Content-Type","application/json; charset=uft-8")
				.log().all();
			
		}
		
	//3) Post Request body using POJO Class
		//@Test(priority=1)
		void testPostusingPOJO() {
			
			Pojo_PostRequest data=new Pojo_PostRequest();
			
			data.setName("Pratima");
			data.setLocation("France");
			data.setPhone("123456");
			
			String courseArr[]= {"C","C++"};
			data.setCourses(courseArr);
			
			given()
				.contentType("application/json")
				.body(data)              
				
			.when()
				.post("http://localhost:3000/students")
				
			.then()
				.statusCode(201)
				.body("name",equalTo("Pratima"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("123456"))
				.body("courses[0]",equalTo("C"))
				.body("courses[1]",equalTo("C++"))
				//.header("Content-Type","application/json; charset=uft-8")
				.log().all();
			
		}	
		
		//4) Post Request body using External Json file
				@Test(priority=1)
				void testPostusingExternalJsonFile() throws FileNotFoundException {
					
					File f= new File("C:\\Users\\dasde\\OneDrive\\Desktop\\Automation\\REST Assured\\RestAssuredAPITesting\\body.json");
					
					FileReader fr= new FileReader(f);
					
					JSONTokener jt= new JSONTokener(fr);
					
					JSONObject data= new JSONObject(jt);
					
					given()
						.contentType("application/json")
						.body(data.toString())              
						
					.when()
						.post("http://localhost:3000/students")
						
					.then()
						.statusCode(201)
						.body("name",equalTo("Pratima"))
						.body("location", equalTo("France"))
						.body("phone", equalTo("123456"))
						.body("courses[0]",equalTo("C"))
						.body("courses[1]",equalTo("C++"))
						//.header("Content-Type","application/json; charset=uft-8")
						.log().all();
					
				}	
	//deleting newly created user
	//@Test(priority=2)
	void testDelete() {	
	given()
	
	.when()
		.delete("http://localhost:3000/students/0837")
			
	.then()
		.statusCode(200);
	}

}
