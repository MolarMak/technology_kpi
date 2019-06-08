package com.molarmak.coursework.pl;

import java.util.ArrayList;

public class Response {

    public Response(Object data) {
        this.data = data;
        this.status = 1;
        this.errors = null;
    }

    public Response(ArrayList<String> errors) {
        this.data = null;
        this.status = 0;
        this.errors = errors;
    }

    public Response() {
        this.data = null;
        this.status = 1;
        this.errors = null;
    }

    private Object data;
    private int status;
    private ArrayList<String> errors;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
}
