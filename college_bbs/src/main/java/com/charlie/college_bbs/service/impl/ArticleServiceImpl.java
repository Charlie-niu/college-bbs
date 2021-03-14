package com.charlie.college_bbs.service.impl;

import com.charlie.college_bbs.bean.Article;
import com.charlie.college_bbs.bean.extend.ArticleExtend;
import com.charlie.college_bbs.dao.ArticleMapper;
import com.charlie.college_bbs.dao.extend.ArticleExtendMapper;
import com.charlie.college_bbs.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    ArticleMapper articleMapper;
    @Resource
    ArticleExtendMapper articleExtendMapper;

    @Override
    public List<Article> findAll() {
        return articleExtendMapper.selectAll();
    }

    @Override
    public List<Article> findArticlesByTitle(String name) {
        return articleExtendMapper.selectByArticleName(name);
    }

    @Override
    public Article findArticleById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public ArticleExtend findArticleExtendById(Integer id) {
        return articleExtendMapper.selectArticleExtendByPrimaryKey(id);
    }

    @Override
    public List<ArticleExtend> findAllArticleByUser(Integer authorid) {
        return articleExtendMapper.selectArticleExtendByUser(authorid);
    }

    @Override
    public int updataArticle(Article article) {
        Article result = findArticleById(article.getId());
        if (result == null){
            article.setReleasedate(new Date().getTime());
            article.setLastdate(article.getReleasedate());
            article.setBrowsenumber(0);
            article.setAgreenumber(0);
            article.setCommentnumber(0);
            if (article.getIssubmitted() == null) {
                article.setIssubmitted(false);
            }
            return articleMapper.insert(article);
        } else {
            article.setLastdate(new Date().getTime());
            return articleMapper.updateByPrimaryKey(article);
        }
    }

}
