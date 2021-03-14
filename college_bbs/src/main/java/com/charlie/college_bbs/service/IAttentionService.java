package com.charlie.college_bbs.service;

public interface IAttentionService {

    public Integer addAttention(Integer bodyId, Integer userId, String bodyType);

    public Integer cancelAttention(Integer bodyId, Integer userId, String bodyType);

}
