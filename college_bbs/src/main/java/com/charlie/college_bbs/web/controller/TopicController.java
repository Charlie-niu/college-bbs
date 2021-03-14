package com.charlie.college_bbs.web.controller;

import com.charlie.college_bbs.bean.Topic;
import com.charlie.college_bbs.service.ITopicService;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private ITopicService topicService;

    //首页需要查询所有文章（不包括文章的栏目、评论）
    @ApiOperation(value = "查询所有话题")
    @GetMapping("findAll")
    public Message findAll(){
        List<Topic> list = topicService.findAll();
        return MessageUtil.success(list);
    }

    @GetMapping("findTopicById")
    public Message findTopicById(Integer topicId){
        Topic topic = topicService.findTopicById(topicId);
        return MessageUtil.success(topic);
    }

    @GetMapping("findTopicesByTag")
    public Message findTopicByTag(Integer tagId){
        List<Topic> topicList = topicService.findTopicByTag(tagId);
        return MessageUtil.success(topicList);
    }

    @PostMapping("findAllTopicByUser")
    public Message findAllTopicByUser(Integer authorid){
        List<Topic> topicList = topicService.findAllTopicByUser(authorid);
        return MessageUtil.success(topicList);
    }


    @ApiOperation(value = "更新话题，如果文章Id不存在，则添加话题")
    @PostMapping("updataTopic")
    public Message updataTopic(Topic topic){
        int stateNums = topicService.updataTopic(topic);
        return MessageUtil.success(stateNums);
    }

}
