package com.charlie.college_bbs.service.impl;

import com.charlie.college_bbs.bean.Discuss;
import com.charlie.college_bbs.bean.extend.DiscussExtend;
import com.charlie.college_bbs.dao.DiscussMapper;
import com.charlie.college_bbs.dao.extend.DiscussExtendMapper;
import com.charlie.college_bbs.service.IDiscussService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DiscussServiceImpl implements IDiscussService {

    @Resource
    DiscussMapper discussMapper;
    @Resource
    DiscussExtendMapper discussExtendMapper;

    @Override
    public List<Discuss> findAll() {
        return discussExtendMapper.selectAll();
    }

    @Override
    public DiscussExtend findDiscussExtendById(Integer id) {
        return discussExtendMapper.selectDiscussExtendByPrimaryKey(id);
    }

    @Override
    public Discuss findDiscussById(Integer discussId) {
        return discussMapper.selectByPrimaryKey(discussId);
    }

    @Override
    public List<Discuss> findDiscussWithTopicId(Integer topicId) {
        return discussExtendMapper.selectDiscussWithTopicId(topicId);
    }

    @Override
    public List<DiscussExtend> findAllDiscussByUser(Integer authorid) {
        return discussExtendMapper.selectAllDiscussByUser(authorid);
    }

    @Override
    public int updataDiscuss(Discuss discuss) {
        Discuss result = discussMapper.selectByPrimaryKey(discuss.getId());
        if (result == null){
            discuss.setReleasedate(new Date().getTime());
            discuss.setLastdate(discuss.getReleasedate());
            if (discuss.getIssubmitted() == null) {
                discuss.setIssubmitted(false);
            }
            discuss.setBrowsenumber(0);
            discuss.setAgreenumber(0);
            discuss.setCommentnumber(0);
            return discussMapper.insert(discuss);
        } else {
            discuss.setLastdate(new Date().getTime());
            return discussMapper.updateByPrimaryKey(discuss);
        }
    }

    @Override
    public int updataDiscussAgreenumber(DiscussExtend discussExtend) {
        return discussExtendMapper.setDiscussAgreenumber(discussExtend);
    }

}
