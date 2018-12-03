package com.TYServer.dto;

public class ErrorResponseParam {
    private Object data;
    private String msg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrorResponseParam{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
