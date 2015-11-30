/**
 * StudentServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alprojects;

public class StudentServiceServiceLocator extends org.apache.axis.client.Service implements com.alprojects.StudentServiceService {

    public StudentServiceServiceLocator() {
    }


    public StudentServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public StudentServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for StudentServicePort
    private java.lang.String StudentServicePort_address = "http://alebedev:8080/My1stWeb/StudentServiceService";

    public java.lang.String getStudentServicePortAddress() {
        return StudentServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String StudentServicePortWSDDServiceName = "StudentServicePort";

    public java.lang.String getStudentServicePortWSDDServiceName() {
        return StudentServicePortWSDDServiceName;
    }

    public void setStudentServicePortWSDDServiceName(java.lang.String name) {
        StudentServicePortWSDDServiceName = name;
    }

    public com.alprojects.StudentService getStudentServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(StudentServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getStudentServicePort(endpoint);
    }

    public com.alprojects.StudentService getStudentServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.alprojects.StudentServicePortBindingStub _stub = new com.alprojects.StudentServicePortBindingStub(portAddress, this);
            _stub.setPortName(getStudentServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setStudentServicePortEndpointAddress(java.lang.String address) {
        StudentServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.alprojects.StudentService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.alprojects.StudentServicePortBindingStub _stub = new com.alprojects.StudentServicePortBindingStub(new java.net.URL(StudentServicePort_address), this);
                _stub.setPortName(getStudentServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("StudentServicePort".equals(inputPortName)) {
            return getStudentServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://alprojects.com/", "StudentServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://alprojects.com/", "StudentServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("StudentServicePort".equals(portName)) {
            setStudentServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
