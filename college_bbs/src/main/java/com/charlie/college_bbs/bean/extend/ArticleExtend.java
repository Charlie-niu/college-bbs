package com.charlie.college_bbs.bean.extend;

import com.charlie.college_bbs.bean.Article;
import com.charlie.college_bbs.utils.BBSBody;
import com.charlie.college_bbs.utils.Base64Utils;

public class ArticleExtend extends Article implements BBSBody{

    @Override
    public void setPicurl(String picurl) {
        super.setPicurl(Base64Utils.ImageToBase64Str(picurl));
    }

    @Override
    public int getBBSBodyBrowsenumber() {
        return this.getBrowsenumber();
    }
}
