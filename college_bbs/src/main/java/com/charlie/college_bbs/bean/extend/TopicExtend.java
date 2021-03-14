package com.charlie.college_bbs.bean.extend;

import com.charlie.college_bbs.bean.Topic;
import com.charlie.college_bbs.utils.BBSBody;

public class TopicExtend extends Topic implements BBSBody {

    @Override
    public int getBBSBodyBrowsenumber() {
        return this.getBrowsenumber();
    }
}
