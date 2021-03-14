package com.charlie.college_bbs.bean.extend;

import com.charlie.college_bbs.bean.Discuss;
import com.charlie.college_bbs.utils.BBSBody;

public class DiscussExtend  extends Discuss implements BBSBody{


    private String topicname;

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    @Override
    public int getBBSBodyBrowsenumber() {
        return this.getBrowsenumber();
    }
}
