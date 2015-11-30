/**
 * StudentService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alprojects;

public interface StudentService extends java.rmi.Remote {
    public boolean appendStudent2(java.lang.String arg0, java.lang.Integer arg1) throws java.rmi.RemoteException;
    public com.alprojects.Student[] getStudents() throws java.rmi.RemoteException;
    public boolean appendStudent(com.alprojects.Student arg0) throws java.rmi.RemoteException;
}
