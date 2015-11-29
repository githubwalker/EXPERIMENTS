package com.alprojects.spring_jdbc;

import java.util.List;

import javax.sql.DataSource;

public interface IStudentDAO {
	void setDataSource(DataSource ds);
	public int create(String name, Integer age);
	public Student getStudent(Integer id);
	public List<Student> listStudents();
	public int delete(Integer id);
	public int update(Integer id, Integer age);
}
