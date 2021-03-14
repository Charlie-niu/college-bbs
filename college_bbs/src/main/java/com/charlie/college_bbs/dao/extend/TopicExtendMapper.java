package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.Topic;
import com.charlie.college_bbs.utils.BBSBody;

import java.util.List;

public interface TopicExtendMapper {

    public List<Topic> selectAll();

    public List<Topic> selectByTopicName(String name);

    public List<Topic> selectTopicByTagId(String tagStr);

    public List<Topic> selectAllTopicByUser(Integer authorid);

    public List<BBSBody> selectAllElementWithNoSubmittedByUser(Integer authorid);
}
