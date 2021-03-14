package com.charlie.college_bbs.service;

import com.charlie.college_bbs.bean.User;
import com.charlie.college_bbs.bean.extend.UserExtend;

import java.util.List;

public interface IUserService {
    /**
     * 查询所有用户
     * @return
     */
    public List<User> findAll();

    /**
     * 用户注册
     * @param userExtend
     * @return
     */
    public int register(UserExtend userExtend);

    public UserExtend findUserByUserName(String username);

}
