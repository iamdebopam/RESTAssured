package day6;

import org.testng.annotations.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//Pojo ---Serilize--> JSON Object --- Deserilize --> Pojo

public class SerilizationDeserilization {
	
	//@Test
	void covertPojo2Json() throws JsonProcessingException {
		
		//created java object using pojo
		student data=new student();
		
		data.setName("Pratima");
		data.setLocation("France");
		data.setPhone("123456");
		
		String courseArr[]= {"C","C++"};
		data.setCourses(courseArr);
		
		//covert java object to json object
		ObjectMapper objMapper = new ObjectMapper();
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		System.out.println(jsondata);
	}
	
	@Test
	void covertJson2Pojo() throws JsonProcessingException {
		
		String jsonData="{\r\n"
				+ "  \"name\" : \"Pratima\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"123456\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
		
		//convert json data ---> POJO Object
		ObjectMapper objMapper= new ObjectMapper();
		
		student st = objMapper.readValue(jsonData, student.class);
		System.out.println("Name:"+st.getName());
		System.out.println("Name:"+st.getLocation());
		System.out.println("Name:"+st.getPhone());
		System.out.println("Name:"+st.getCourses()[0]);
	}


}
