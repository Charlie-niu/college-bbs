package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.Article;
import com.charlie.college_bbs.bean.extend.ArticleExtend;
import com.charlie.college_bbs.utils.BBSBody;

import java.util.List;

public interface ArticleExtendMapper {
    public List<Article> selectAll();

    public List<Article> selectByArticleName(String name);


    public ArticleExtend selectArticleExtendByPrimaryKey(Integer id);

    public List<ArticleExtend> selectArticleExtendByUser(Integer authorid);

    public List<BBSBody> selectAllElement();

    public List<BBSBody> selectAllElementWithNoSubmittedByUser(Integer authorid);

    public List<BBSBody> selectAllAttentionArticleByUser(Integer userid);
}
