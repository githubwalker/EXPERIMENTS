package com.alprojects.test_json;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class TestJsonSerialize {

	
	static public String testJsonSerialize() 
	{
		ObjectMapper om = new ObjectMapper();
		Student stud = new Student();
		stud.setAge(15);
		stud.setId(1);
		stud.setName("Vasya");

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		
		ArrayList<Student> studs = new ArrayList<Student>();
		
		studs.add( new Student( "Vasya", 15, 1 ) );
		studs.add( new Student( "Galya", 25, 2 ) );
		
		JtableResponse jr = new JtableResponse("OK", studs);
		
		try {
			// json = ow.writeValueAsString(stud);
			json = ow.writeValueAsString(jr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
}


