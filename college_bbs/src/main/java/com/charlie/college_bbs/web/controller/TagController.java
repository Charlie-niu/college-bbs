package com.charlie.college_bbs.web.controller;

import com.charlie.college_bbs.bean.Tag;
import com.charlie.college_bbs.service.ITagService;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "标签相关内容")
@RestController()
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private ITagService tagService;


    @ApiOperation(value = "查询所有标签")
    @GetMapping("findAll")
    public Message findAll(){
        List<Tag> list = tagService.findAll();
        return MessageUtil.success(list);
    }

    @ApiOperation(value = "查询所有标签")
    @GetMapping("findTags")
    public Message findTags(String tags){
        log.info("findTags被调用:" + tags);
        List<Tag> list = tagService.findTags(tags);
        return MessageUtil.success(list);
    }


}
