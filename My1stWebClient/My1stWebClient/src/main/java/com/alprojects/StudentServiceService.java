/**
 * StudentServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alprojects;

public interface StudentServiceService extends javax.xml.rpc.Service {
    public java.lang.String getStudentServicePortAddress();

    public com.alprojects.StudentService getStudentServicePort() throws javax.xml.rpc.ServiceException;

    public com.alprojects.StudentService getStudentServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
