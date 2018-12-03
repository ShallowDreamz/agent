package com.TYServer.utils;

public class MyException extends Exception {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MyException(String code) {
        this.code = code;
    }

    public MyException(String message, String code) {
        super(message);
        this.code = code;
    }

    public MyException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public MyException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }
}
