package com.charlie.college_bbs.service.impl;

import com.charlie.college_bbs.bean.Topic;
import com.charlie.college_bbs.dao.TopicMapper;
import com.charlie.college_bbs.dao.extend.TopicExtendMapper;
import com.charlie.college_bbs.service.ITopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService {

    @Resource
    TopicExtendMapper topicExtendMapper;

    @Resource
    TopicMapper topicMapper;

    @Override
    public List<Topic> findAll() {
        return topicExtendMapper.selectAll();
    }

    @Override
    public List<Topic> findTopicByTitle(String name) {
        return topicExtendMapper.selectByTopicName(name);
    }

    @Override
    public Topic findTopicById(Integer id) {
        return topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Topic> findTopicByTag(Integer tagId) {
        String tagStr = Integer.toString(tagId);
        return topicExtendMapper.selectTopicByTagId(tagStr);
    }

    @Override
    public List<Topic> findAllTopicByUser(Integer authorid) {
        return topicExtendMapper.selectAllTopicByUser(authorid);
    }

    @Override
    public Integer updataTopic(Topic topic) {
        Topic result = findTopicById(topic.getId());
        if (result == null){
            topic.setReleasedate(new Date().getTime());
            topic.setLastdate(topic.getReleasedate());
            if (topic.getIssubmitted() == null) {
                topic.setIssubmitted(false);
            }
            topic.setBrowsenumber(0);
            topic.setCommentnumber(0);
            return topicMapper.insert(topic);
        } else {
            topic.setLastdate(new Date().getTime());
            return topicMapper.updateByPrimaryKey(topic);
        }
    }
}
