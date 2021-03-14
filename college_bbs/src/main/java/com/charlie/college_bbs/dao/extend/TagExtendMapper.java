package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.Tag;

import java.util.List;

public interface TagExtendMapper {

    List<Tag> selectAll();

    List<Tag> selectTags(Integer[] tagsId);
}
