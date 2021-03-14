package com.charlie.college_bbs.service;

import com.charlie.college_bbs.bean.Comment;
import com.charlie.college_bbs.bean.extend.CommentExtend;

import java.util.List;

public interface ICommentService {
    public List<CommentExtend> findAllByElement(Integer elementId, String elementType);

    public List<Comment> findCommentByUser(Integer userId);

    public Integer updataComment(Comment comment);

    public Integer deleteComment(Integer commentId);

    public Comment findCommentById(Integer id);

}
