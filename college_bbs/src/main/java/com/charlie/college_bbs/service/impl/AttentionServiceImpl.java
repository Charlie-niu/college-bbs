package com.charlie.college_bbs.service.impl;

import com.charlie.college_bbs.bean.Attention;
import com.charlie.college_bbs.dao.AttentionMapper;
import com.charlie.college_bbs.dao.extend.AttentionExtendMapper;
import com.charlie.college_bbs.service.IAttentionService;
import com.charlie.college_bbs.utils.CustomerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AttentionServiceImpl implements IAttentionService {

    @Resource
    AttentionMapper attentionMapper;
    @Resource
    AttentionExtendMapper attentionExtendMapper;

    @Override
    public Integer addAttention(Integer bodyId, Integer userId, String bodyType) {
        Attention attention = new Attention();
        attention.setBodyid(bodyId);
        attention.setUserid(userId);
        attention.setBodytype(bodyType);
        Attention result = attentionExtendMapper.selectAttention(attention);
        if (result == null) {
            return attentionMapper.insert(attention);
        }
        else {
            throw new CustomerException("已关注！！");
        }
    }

    @Override
    public Integer cancelAttention(Integer bodyId, Integer userId, String bodyType) {
        Attention attention = new Attention();
        attention.setBodyid(bodyId);
        attention.setUserid(userId);
        attention.setBodytype(bodyType);
        Attention result = attentionExtendMapper.selectAttention(attention);
        if (result != null) {
            return attentionMapper.deleteByPrimaryKey(result.getId());
        } else {
            throw new CustomerException("数据错误！取消关注失败");
        }
    }
}
