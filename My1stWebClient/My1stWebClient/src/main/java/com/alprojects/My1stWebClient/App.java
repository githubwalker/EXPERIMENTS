package com.alprojects.My1stWebClient;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.alprojects.Student;
import com.alprojects.StudentService;
import com.alprojects.StudentServiceServiceLocator;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		StudentServiceServiceLocator ssl = new StudentServiceServiceLocator();
		try {
			System.out.println("Before getPort");
			StudentService ss = (StudentService) ssl
					.getPort(StudentService.class);
			System.out.println("Before getStudents");
			Student[] studs = ss.getStudents();

			for (Student stud : studs) {
				StringBuilder sb = new StringBuilder();
				sb.append(stud.getId());
				sb.append("). name: ");
				sb.append(stud.getName());
				sb.append(", age: ");
				sb.append(stud.getAge());
				System.out.println(sb.toString());
			}

			System.out.println("Before add new user");
			ss.appendStudent(new Student(11, 0, "Newbie"));

			return;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.err.println("ServiceException happened:");
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.err.println("RemoteException happened:");
			e.printStackTrace();
		}
	}
}
