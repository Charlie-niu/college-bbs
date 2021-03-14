package com.charlie.college_bbs.service;

import com.charlie.college_bbs.bean.Article;
import com.charlie.college_bbs.bean.extend.ArticleExtend;

import java.util.List;


public interface IArticleService {
    public List<Article> findAll();

    public List<Article> findArticlesByTitle(String name);

    public Article findArticleById(Integer id);

    public ArticleExtend findArticleExtendById(Integer id);

    public int updataArticle(Article article);

    public List<ArticleExtend> findAllArticleByUser(Integer authorid);
}
