package com.alprojects;

public class StudentServiceProxy implements com.alprojects.StudentService {
  private String _endpoint = null;
  private com.alprojects.StudentService studentService = null;
  
  public StudentServiceProxy() {
    _initStudentServiceProxy();
  }
  
  public StudentServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initStudentServiceProxy();
  }
  
  private void _initStudentServiceProxy() {
    try {
      studentService = (new com.alprojects.StudentServiceServiceLocator()).getStudentServicePort();
      if (studentService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)studentService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)studentService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (studentService != null)
      ((javax.xml.rpc.Stub)studentService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.alprojects.StudentService getStudentService() {
    if (studentService == null)
      _initStudentServiceProxy();
    return studentService;
  }
  
  public boolean appendStudent2(java.lang.String arg0, java.lang.Integer arg1) throws java.rmi.RemoteException{
    if (studentService == null)
      _initStudentServiceProxy();
    return studentService.appendStudent2(arg0, arg1);
  }
  
  public com.alprojects.Student[] getStudents() throws java.rmi.RemoteException{
    if (studentService == null)
      _initStudentServiceProxy();
    return studentService.getStudents();
  }
  
  public boolean appendStudent(com.alprojects.Student arg0) throws java.rmi.RemoteException{
    if (studentService == null)
      _initStudentServiceProxy();
    return studentService.appendStudent(arg0);
  }
  
  
}