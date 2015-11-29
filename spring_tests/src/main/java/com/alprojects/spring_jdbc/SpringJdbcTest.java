package com.alprojects.spring_jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJdbcTest {

	public static void tests() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Jdbc.xml");
		StudentJdbcDAO studDAO = (StudentJdbcDAO) context.getBean("studentJDBCDAO");

		int nCount = 0;
		// nCount = studDAO.create("Dron", 17);
		// nCount = studDAO.create("Sunny", 18);

		List<Student> studs = studDAO.listStudents();

		for (Student stud : studs) {
			System.out.println(stud.toString());
		}

		return;
	}
}


