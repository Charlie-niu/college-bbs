package com.charlie.college_bbs.service;

import com.charlie.college_bbs.bean.Discuss;
import com.charlie.college_bbs.bean.extend.DiscussExtend;

import java.util.List;

public interface IDiscussService {
    public List<Discuss> findAll();

    public int updataDiscuss(Discuss discuss);

    public int updataDiscussAgreenumber(DiscussExtend discussExtend);

    public DiscussExtend findDiscussExtendById(Integer id);

    public Discuss findDiscussById(Integer discussId);

    public List<Discuss> findDiscussWithTopicId(Integer topicId);

    public List<DiscussExtend> findAllDiscussByUser(Integer authorid);
}
