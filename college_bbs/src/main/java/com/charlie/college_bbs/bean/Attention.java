package com.charlie.college_bbs.bean;

public class Attention {
    private Integer id;

    private Integer userid;

    private Integer bodyid;

    private String bodytype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBodyid() {
        return bodyid;
    }

    public void setBodyid(Integer bodyid) {
        this.bodyid = bodyid;
    }

    public String getBodytype() {
        return bodytype;
    }

    public void setBodytype(String bodytype) {
        this.bodytype = bodytype == null ? null : bodytype.trim();
    }
}