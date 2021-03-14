package com.charlie.college_bbs.utils;

import com.charlie.college_bbs.dao.extend.ArticleExtendMapper;
import com.charlie.college_bbs.dao.extend.DiscussExtendMapper;
import com.charlie.college_bbs.dao.extend.TopicExtendMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 论坛主体的Controller类，当前端访问首页的时候，会发送一个获取论坛主体内容列表的请求到这个Controller，
 * 这个Controller会去数据库中获取到论坛主体的集合，并按照浏览量降序排列，返回给前端，前端展示数据
 */
@Slf4j
@RestController
@RequestMapping("/bbs")
public class BBSBodyController {

    @Resource
    ArticleExtendMapper articleExtendMapper;
    @Resource
    DiscussExtendMapper discussExtendMapper;
    @Resource
    TopicExtendMapper topicExtendMapper;

    public static List<BBSBody> bbsBodyList;

    @GetMapping("findAll")
    public Message findAll(){
        bbsBodyList = articleExtendMapper.selectAllElement();
        bbsBodyList.addAll(discussExtendMapper.selectAllElement());
        bbsBodyList.sort((b1,b2)->b2.getBBSBodyBrowsenumber()-b1.getBBSBodyBrowsenumber());
        return MessageUtil.success(bbsBodyList);
    }

    @PostMapping("findAllDraftByUser")
    public Message findAllDraftByUser(Integer authorid){
        bbsBodyList = articleExtendMapper.selectAllElementWithNoSubmittedByUser(authorid);
        bbsBodyList.addAll(discussExtendMapper.selectAllElementWithNoSubmittedByUser(authorid));
        bbsBodyList.sort((b1,b2)->b2.getBBSBodyBrowsenumber()-b1.getBBSBodyBrowsenumber());
        bbsBodyList.addAll(topicExtendMapper.selectAllElementWithNoSubmittedByUser(authorid));
        bbsBodyList.sort((b1,b2)->b2.getBBSBodyBrowsenumber()-b1.getBBSBodyBrowsenumber());
        return MessageUtil.success(bbsBodyList);
    }

    @PostMapping("findAllAttentionByUser")
    public Message findAllAttentionByUser(Integer userid){
        bbsBodyList = articleExtendMapper.selectAllAttentionArticleByUser(userid);
        List<BBSBody> discussList = discussExtendMapper.selectAllAttentionDiscussByUser(userid);
        bbsBodyList.addAll(discussList);
        bbsBodyList.sort((b1,b2)->b2.getBBSBodyBrowsenumber()-b1.getBBSBodyBrowsenumber());
        return MessageUtil.success(bbsBodyList);
    }

}
