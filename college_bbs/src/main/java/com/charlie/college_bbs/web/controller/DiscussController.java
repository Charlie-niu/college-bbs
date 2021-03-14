package com.charlie.college_bbs.web.controller;

import com.charlie.college_bbs.bean.Discuss;
import com.charlie.college_bbs.bean.extend.DiscussExtend;
import com.charlie.college_bbs.service.IDiscussService;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "回答相关内容")
@RestController
@RequestMapping("/discuss")
public class DiscussController {

    @Autowired
    private IDiscussService discussService;

    //首页需要查询所有文章（不包括文章的栏目、评论）
    @ApiOperation(value = "查询所有文章（不包括文章的栏目、评论）")
    @GetMapping("findAll")
    public Message findAll(){
        List<Discuss> list = discussService.findAll();
        return MessageUtil.success(list);
    }

    @GetMapping("findDiscussById")
    public Message findDiscussById(Integer discussId){
        log.info("discussId" + discussId);
        System.out.println();
        DiscussExtend discussExtend = discussService.findDiscussExtendById(discussId);
        return MessageUtil.success(discussExtend);
    }

    @GetMapping("findDiscussWithTopicId")
    public Message findDiscussWithTopicId(Integer topicId){
        List<Discuss> discussList = discussService.findDiscussWithTopicId(topicId);
        return MessageUtil.success(discussList);
    }

    @PostMapping("findAllDiscussByUser")
    public Message findAllDiscussByUser(Integer authorid){
        List<DiscussExtend> topicList = discussService.findAllDiscussByUser(authorid);
        return MessageUtil.success(topicList);
    }

    @ApiOperation(value = "更新文章，如果文章Id不存在，则添加文章")
    @PostMapping("updataDiscuss")
    public Message updataArticle(Discuss discuss){
        log.info("访问updataArticle控制器:" + discuss.getTopicid());
        int stateNums = discussService.updataDiscuss(discuss);
        return MessageUtil.success(stateNums);
    }

    @GetMapping("addBrowsenumber")
    public Message updataDiscussBrowsenumber(Integer discussId){
        Discuss discuss = discussService.findDiscussById(discussId);
        System.out.println(discuss);
        discuss.setBrowsenumber(discuss.getBrowsenumber() + 1);
        int stateNum = discussService.updataDiscuss(discuss);
        return MessageUtil.success(stateNum);
    }
    @PostMapping("setAgreenumber")
    public Message setAgreenumber(Integer discussId, Integer value){
        log.info("com.charlie.college_bbs.web.controller.DiscussController.setAgreenumber方法被调用：" + discussId);
        DiscussExtend discussExtend = discussService.findDiscussExtendById(discussId);

        System.out.println(discussExtend.getAgreenumber() + value);
        discussExtend.setAgreenumber(discussExtend.getAgreenumber() + value);
        int result = discussService.updataDiscussAgreenumber(discussExtend);
        return MessageUtil.success(result);
    }
}
