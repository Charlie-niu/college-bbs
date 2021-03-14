package com.charlie.college_bbs.service;

import com.charlie.college_bbs.bean.Tag;

import java.util.List;

public interface ITagService {

    public List<Tag> findAll();

    public List<Tag> findTags(String tags);
}
