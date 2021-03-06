package com.alprojects.oldapi;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.alprojects.data.JsonBuilder;
import com.alprojects.data.Student;
import com.alprojects.data.StudentJdbcDAO;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sun.jersey.api.client.ClientResponse.Status;


// http://www.simplecodestuffs.com/ajax-based-crud-operations-in-jsp-and-servlet-using-jtable-jquery-plug-in/
// http://stackoverflow.com/questions/21415413/access-file-inside-webcontent-from-servlet
// http://localhost:8080/StudentService/crunchify/students

// REST
// http://www.programming-free.com/2013/08/ajax-based-crud-operations-in-java-web.html
// https://github.com/hikalkan/jtable/issues/772
// http://crunchify.com/create-very-simple-jersey-rest-service-and-send-json-data-from-java-client/
// https://www.nabisoft.com/tutorials/java-ee/producing-and-consuming-json-or-xml-in-java-rest-services-with-jersey-and-jackson

// AUTH
// http://devcolibri.com/3810

@Path("/students")
public class StudentService {
	private StudentJdbcDAO studDAO;

	public StudentService( @Context ServletContext ctx ) {
		
		String strPath = ctx.getRealPath("/WEB-INF/jdbc.xml");
		ApplicationContext context =
				new FileSystemXmlApplicationContext(strPath);
		studDAO = (StudentJdbcDAO) context.getBean("studentJDBCDAO");
	}

	private static String nonnull( String str )	{ return str == null ? "<null>" : str;}
	private static Integer nonnull( Integer intgr )
	{
		return intgr == null ? 0 : intgr;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPage/{startIndex}/{numberItems}")
	public Response getPage(
			@PathParam("startIndex") Integer startIndex,
			@PathParam("numberItems") Integer numberItems
	)
	{
		try {
			Integer nStuds = studDAO.getTotalStudents();
			String strResponse = new JsonBuilder()
					.add("Result", "OK" )
					.add("TotalRecordCount", nStuds )
					.add( "Records", studDAO.listStudentsPaged(startIndex, numberItems) )
					.build();
			return Response.ok(strResponse, MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			e.printStackTrace();
			if ( e instanceof JsonProcessingException )
			{
				System.out.println("Unsupported media type");
				return Response.status(Status.UNSUPPORTED_MEDIA_TYPE).build();
			}
		}

		return Response.serverError().build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getall")
	public Response getStudents()
	{
		try {
			String strResponse = new JsonBuilder()
					.add("Result", "OK" )
					.add("Records", studDAO.listStudents())
					.build();
			return Response.ok(strResponse, MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	}
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/append")
	public Response appendStudent( 
			String strstud
			) {
		System.out.println( "appendStudent. object received : " + nonnull(strstud) );
		
		ObjectMapper mapper = new ObjectMapper();
		StudentAdd sa;

		try {
			sa = mapper.readValue(strstud, StudentAdd.class);
			int id = studDAO.create(sa.getName(), sa.getAge());
			Student stud = new Student(id, sa.getName(), sa.getAge());
			String strResponse = new JsonBuilder()
					.add( "Result", "OK" )
					.add( "Record", stud )
					.build();
			return Response.ok(strResponse, MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			if ( e instanceof JsonProcessingException )
			{
				System.out.println("Unsupported media type");
				e.printStackTrace();
				return Response.status(Status.UNSUPPORTED_MEDIA_TYPE).build();
			}
		}
		return Response.serverError().build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update/{id}")
	public Response updateStudent(
			@PathParam("id") Integer id,
			String strstud
	) {
		System.out.println( "updateStudent. object received : " + nonnull(strstud) );

		ObjectMapper mapper = new ObjectMapper();
		Student stud_in;

		try {
			stud_in = mapper.readValue(strstud, Student.class);
			studDAO.update( id, stud_in.getName(), stud_in.getAge() );
			String strResponse = new JsonBuilder()
					.add( "Result", "OK" )
					.add( "Record", stud_in )
					.build();
			return Response.ok(strResponse, MediaType.APPLICATION_JSON).build();
		} catch (IOException e) {
			if ( e instanceof JsonProcessingException )
			{
				System.out.println("Unsupported media type");
				e.printStackTrace();
				return Response.status(Status.UNSUPPORTED_MEDIA_TYPE).build();
			}
		}
		return Response.serverError().build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public Response deleteStudent(
			@PathParam("id") Integer id
	)
	{
		System.out.println( "deleteStudent. id received : " + nonnull(id) );
		try {
			studDAO.delete( id );
			String strResponse = new JsonBuilder()
					.add( "Result", "OK" )
					.build();
			return Response.ok(strResponse, MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			// something wrong happened
		}

		return Response.serverError().build();
	}
}

