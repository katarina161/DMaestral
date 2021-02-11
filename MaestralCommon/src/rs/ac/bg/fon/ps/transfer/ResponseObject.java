/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.transfer;

import java.io.Serializable;
import rs.ac.bg.fon.ps.util.Operation;

/**
 *
 * @author Katarina
 */
public class ResponseObject implements Serializable{
    
    private Operation operation;
    private Object result;
    private Exception exception;
    private String message;

    public ResponseObject() {
    }

    public ResponseObject(Operation operation, Object result, Exception exception, String message) {
        this.operation = operation;
        this.result = result;
        this.exception = exception;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
}
