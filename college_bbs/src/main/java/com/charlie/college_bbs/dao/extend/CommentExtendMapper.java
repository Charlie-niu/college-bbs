package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.extend.CommentExtend;

import java.util.List;

public interface CommentExtendMapper {

    public List<CommentExtend> findCommentsByArticleID(Integer elementId);

    public List<CommentExtend> findCommentsByDiscussID(Integer elementId);
}
