package com.TYServer.dto;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
    private Integer pageCount;
    private Integer pageNum;
    private List object;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List getObject() {
        return object;
    }

    public void setObject(List object) {
        this.object = object;
    }
}
