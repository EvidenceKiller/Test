package com.adl.service.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/3/18
 * Describe   : 类描述
 */
public class PageInfo<T> {

    private int current;
    private int pages;
    private int size;
    private int total;
    private List<T> records;

    public PageInfo() {
        records = new ArrayList<>();
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
