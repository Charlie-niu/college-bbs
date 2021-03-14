package com.charlie.college_bbs.web.controller;

import com.charlie.college_bbs.bean.Article;
import com.charlie.college_bbs.bean.extend.ArticleExtend;
import com.charlie.college_bbs.service.IArticleService;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "文章相关内容")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    //首页需要查询所有文章（不包括文章的栏目、评论）
    @ApiOperation(value = "查询所有文章（不包括文章的栏目、评论）")
    @GetMapping("findAll")
    public Message findAll(){
        List<Article> list = articleService.findAll();
        return MessageUtil.success(list);
    }

    @ApiOperation(value = "查询所有文章（不包括文章的栏目、评论）")
    @GetMapping("findArticlesByTitle")
    public Message findArticlesByTitle(String articleName){
        List<Article> list = articleService.findArticlesByTitle(articleName);
        return MessageUtil.success(list);
    }

    @GetMapping("findArticleById")
    public Message findArticleById(Integer articleId){
        ArticleExtend articleExtend = articleService.findArticleExtendById(articleId);
        return MessageUtil.success(articleExtend);
    }

    @PostMapping("findAllArticleByUser")
    public Message findAllArticleByUser(Integer authorid){
        List<ArticleExtend> articleExtendList = articleService.findAllArticleByUser(authorid);
        return MessageUtil.success(articleExtendList);
    }

    @ApiOperation(value = "更新文章，如果文章Id不存在，则添加文章")
    @PostMapping("updataArticle")
    public Message updataArticle(Article article){
        log.info("正在跟新文章" + article.getPicurl());
        int stateNums = articleService.updataArticle(article);
        return MessageUtil.success(stateNums);
    }
    @GetMapping("addBrowsenumber")
    public Message updataArticleBrowsenumber(Integer articleId){
        Article article = articleService.findArticleById(articleId);
        System.out.println(article);
        article.setBrowsenumber(article.getBrowsenumber() + 1);
        int stateNum = articleService.updataArticle(article);
        return MessageUtil.success(stateNum);
    }

    @PostMapping("setAgreenumber")
    public Message setAgreenumber(Integer articleId, Integer value){
        log.info("com.charlie.college_bbs.web.controller.ArticleController.setAgreenumber方法被调用：" + articleId);
        Article article = articleService.findArticleById(articleId);
        article.setAgreenumber(article.getAgreenumber() + value);
        int result = articleService.updataArticle(article);
        return MessageUtil.success(result);
    }


}
