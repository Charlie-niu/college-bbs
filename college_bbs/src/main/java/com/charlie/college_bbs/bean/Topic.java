package com.charlie.college_bbs.bean;

public class Topic {
    private Integer id;

    private String topicname;

    private Integer authorid;

    private String tagid;

    private String description;

    private Boolean issubmitted;

    private Long releasedate;

    private Long lastdate;

    private Integer browsenumber;

    private Integer commentnumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname == null ? null : topicname.trim();
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid == null ? null : tagid.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getIssubmitted() {
        return issubmitted;
    }

    public void setIssubmitted(Boolean issubmitted) {
        this.issubmitted = issubmitted;
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
}