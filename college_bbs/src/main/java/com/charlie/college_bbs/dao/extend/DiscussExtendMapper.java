package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.Discuss;
import com.charlie.college_bbs.bean.extend.DiscussExtend;
import com.charlie.college_bbs.utils.BBSBody;

import java.util.List;

public interface DiscussExtendMapper {

    public List<Discuss> selectAll();

    public List<BBSBody> selectAllElement();

    public int setDiscussAgreenumber(DiscussExtend discussExtend);

    public DiscussExtend selectDiscussExtendByPrimaryKey(Integer id);

    public List<Discuss> selectDiscussWithTopicId(Integer topicId);

    public List<DiscussExtend> selectAllDiscussByUser(Integer authorid);

    public List<BBSBody> selectAllElementWithNoSubmittedByUser(Integer authorid);

    public List<BBSBody> selectAllAttentionDiscussByUser(Integer userid);

}
