package com.charlie.college_bbs.web.controller;

import com.charlie.college_bbs.bean.Comment;
import com.charlie.college_bbs.bean.extend.CommentExtend;
import com.charlie.college_bbs.service.ICommentService;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("findAllByElement")
    public Message findAllByElement(Integer elementId, String elementType){
        System.out.println("findAllByElement: " + elementId + "," + elementType);
        List<CommentExtend> commentList = commentService.findAllByElement(elementId, elementType);
        return MessageUtil.success(commentList);
    }

    @PostMapping("findCommentByUser")
    public Message findCommentByUser(Integer userId){
        List<Comment> commentList = commentService.findCommentByUser(userId);
        return MessageUtil.success(commentList);
    }

    @PostMapping("updataComment")
    public Message updataComment(Comment comment){
        log.info("正在跟新评论" + comment);
        int stateNums = commentService.updataComment(comment);
        return MessageUtil.success(stateNums);
    }

    @PostMapping("deleteComment")
    public Message deleteComment(Integer commentId){
        log.info("正在删除评论：" + commentId);
        Integer result = commentService.deleteComment(commentId);
        return MessageUtil.success(result);
    }

}
