package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.Attention;
import org.apache.ibatis.annotations.Param;

public interface AttentionExtendMapper {
    public Attention selectAttention(@Param("attention")Attention attention);
}
