package com.charlie.college_bbs.dao;

import com.charlie.college_bbs.bean.Discuss;

public interface DiscussMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Discuss record);

    int insertSelective(Discuss record);

    Discuss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Discuss record);

    int updateByPrimaryKey(Discuss record);
}