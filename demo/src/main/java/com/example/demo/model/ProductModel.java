package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 产品表
 */
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    //产品名
    private String name;
    //编号
    private String number;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
