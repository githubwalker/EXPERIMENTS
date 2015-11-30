package com.alprojects;

import java.util.List;

import javax.jws.WebService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebService
public class StudentService {

	private final StudentJdbcDAO studDAO;

	public StudentService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("../config/jdbc.xml");
		studDAO = (StudentJdbcDAO) context.getBean("studentJDBCDAO");
	}

	public List<Student> getStudents() {
		return studDAO.listStudents();
	}
	
	public boolean appendStudent( Student stud ) {
		return studDAO.create(stud.getName(), stud.getAge()) > 0;
	}
	
	public boolean appendStudent2( String name, Integer age ) {
		return studDAO.create(name, age) > 0;
	}
	
}

