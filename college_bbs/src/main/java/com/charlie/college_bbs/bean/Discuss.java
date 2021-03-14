package com.charlie.college_bbs.bean;

public class Discuss {
    private Integer id;

    private String content;

    private Boolean issubmitted;

    private Integer authorid;

    private Integer topicid;

    private Long releasedate;

    private Long lastdate;

    private Integer browsenumber;

    private Integer commentnumber;

    private Integer agreenumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getIssubmitted() {
        return issubmitted;
    }

    public void setIssubmitted(Boolean issubmitted) {
        this.issubmitted = issubmitted;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public Long getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Long releasedate) {
        this.releasedate = releasedate;
    }

    public Long getLastdate() {
        return lastdate;
    }

    public void setLastdate(Long lastdate) {
        this.lastdate = lastdate;
    }

    public Integer getBrowsenumber() {
        return browsenumber;
    }

    public void setBrowsenumber(Integer browsenumber) {
        this.browsenumber = browsenumber;
    }

    public Integer getCommentnumber() {
        return commentnumber;
    }

    public void setCommentnumber(Integer commentnumber) {
        this.commentnumber = commentnumber;
    }

    public Integer getAgreenumber() {
        return agreenumber;
    }

    public void setAgreenumber(Integer agreenumber) {
        this.agreenumber = agreenumber;
    }
}