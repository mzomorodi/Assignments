/**
 * Lab4ServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package edu.asupoly.ser422.lab4.adb;

public class Lab4ServiceExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1555158372234L;
    private edu.asupoly.ser422.lab4.adb.Lab4ServiceStub.Lab4ServiceException faultMessage;

    public Lab4ServiceExceptionException() {
        super("Lab4ServiceExceptionException");
    }

    public Lab4ServiceExceptionException(java.lang.String s) {
        super(s);
    }

    public Lab4ServiceExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public Lab4ServiceExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        edu.asupoly.ser422.lab4.adb.Lab4ServiceStub.Lab4ServiceException msg) {
        faultMessage = msg;
    }

    public edu.asupoly.ser422.lab4.adb.Lab4ServiceStub.Lab4ServiceException getFaultMessage() {
        return faultMessage;
    }
}
