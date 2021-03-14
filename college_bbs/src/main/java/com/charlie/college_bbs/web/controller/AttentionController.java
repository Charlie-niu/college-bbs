package com.charlie.college_bbs.web.controller;

import com.charlie.college_bbs.service.IAttentionService;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attention")
public class AttentionController {

    @Autowired
    IAttentionService attentionService;

    @PostMapping("addAttention")
    public Message addAttention(Integer bodyId, Integer userId, String bodyType){
        System.out.println("bodyId: " + bodyId + "userId: " + userId + "bodyType: " + bodyType);
        Integer result = attentionService.addAttention(bodyId, userId, bodyType);
        return MessageUtil.success(result);
    }

    @PostMapping("cancelAttention")
    public Message cancelAttention(Integer bodyId, Integer userId, String bodyType){
        System.out.println("bodyId: " + bodyId + "userId: " + userId + "bodyType: " + bodyType);
        Integer result = attentionService.cancelAttention(bodyId, userId, bodyType);
        return MessageUtil.success(result);
    }
}
