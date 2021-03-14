package com.charlie.college_bbs.service;

import com.charlie.college_bbs.bean.Topic;

import java.util.List;

public interface ITopicService {

    public List<Topic> findAll();

    public Integer updataTopic(Topic topic);

    public List<Topic> findTopicByTitle(String topicName);

    public Topic findTopicById(Integer id);

    public List<Topic> findTopicByTag(Integer tagId);

    public List<Topic> findAllTopicByUser(Integer authorid);
}
