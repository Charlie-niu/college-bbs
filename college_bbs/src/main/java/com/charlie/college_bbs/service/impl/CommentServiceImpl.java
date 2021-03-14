package com.charlie.college_bbs.service.impl;

import com.charlie.college_bbs.bean.Comment;
import com.charlie.college_bbs.bean.extend.CommentExtend;
import com.charlie.college_bbs.dao.CommentMapper;
import com.charlie.college_bbs.dao.extend.CommentExtendMapper;
import com.charlie.college_bbs.service.ICommentService;
import com.charlie.college_bbs.utils.CustomerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Resource
    CommentMapper commentMapper;
    @Resource
    CommentExtendMapper commentExtendMapper;

    @Override
    public List<CommentExtend> findAllByElement(Integer elementId, String elementType) {
        if (elementType.equals("article")){
            return commentExtendMapper.findCommentsByArticleID(elementId);
        } else if(elementType.equals("discuss")){
            return commentExtendMapper.findCommentsByDiscussID(elementId);
        }else {
            throw new CustomerException("数据错误，请重试");
        }
    }

    @Override
    public List<Comment> findCommentByUser(Integer userId) {
        return null;
    }

    @Override
    public Integer updataComment(Comment comment) {
        Comment result = findCommentById(comment.getId());
        if (result == null){
            if(comment.getBaseid() == null) {
                comment.setBaseid(0);
            }
            comment.setReleasedate(new Date().getTime());
            return commentMapper.insert(comment);
        } else {
            throw new CustomerException("数据错误，请重试");
        }
    }

    @Override
    public Comment findCommentById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer deleteComment(Integer commentId) {
        return commentMapper.deleteByPrimaryKey(commentId);
    }
}
