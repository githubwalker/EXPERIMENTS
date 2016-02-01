package com.alprojects.test_json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class JtableResponse {

	private String result;
	private List<Student> studs;
	
	public JtableResponse(
			String result,
			List<Student> studs
			)
	{
		this.result = result;
		this.studs = studs;
	}
	
	@JsonProperty("Result")
	public String getResult() { return result; }
	
	@JsonProperty("Records")
	public List<Student> getRecords() { return studs; }
	
}
