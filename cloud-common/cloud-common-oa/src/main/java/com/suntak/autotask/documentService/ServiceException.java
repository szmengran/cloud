
/**
 * ServiceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

package com.suntak.autotask.documentService;

public class ServiceException extends java.lang.Exception{
    
    private com.suntak.autotask.documentService.DocumentServiceStub.ServiceExceptionE faultMessage;
    
    public ServiceException() {
        super("ServiceException");
    }
           
    public ServiceException(java.lang.String s) {
       super(s);
    }
    
    public ServiceException(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(com.suntak.autotask.documentService.DocumentServiceStub.ServiceExceptionE msg){
       faultMessage = msg;
    }
    
    public com.suntak.autotask.documentService.DocumentServiceStub.ServiceExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    