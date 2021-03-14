package com.charlie.college_bbs.service.impl;

import com.charlie.college_bbs.bean.Tag;
import com.charlie.college_bbs.dao.extend.TagExtendMapper;
import com.charlie.college_bbs.service.ITagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements ITagService {

    @Resource
    TagExtendMapper tagExtendMapper;

    @Override
    public List<Tag> findAll() {
        return tagExtendMapper.selectAll();
    }

    @Override
    public List<Tag> findTags(String tags) {
        String[] tagStrs = tags.split(",");
        Integer[] tagIds = new Integer[tagStrs.length];
        for (int i=0; i<tagStrs.length; i++){
            tagIds[i] = Integer.parseInt(tagStrs[i]);
        }
        return tagExtendMapper.selectTags(tagIds);
    }


}
