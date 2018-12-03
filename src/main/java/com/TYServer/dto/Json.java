package com.TYServer.dto;

import java.io.Serializable;

public class Json implements Serializable{
    private Object data;
    private String msg;

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Json{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
