package com.charlie.college_bbs.service.impl;

import com.charlie.college_bbs.bean.User;
import com.charlie.college_bbs.bean.extend.UserExtend;
import com.charlie.college_bbs.dao.UserMapper;
import com.charlie.college_bbs.dao.extend.UserExtendMapper;
import com.charlie.college_bbs.service.IUserService;
import com.charlie.college_bbs.utils.CustomerException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserExtendMapper userExtendMapper;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        User user = userMapper.selectByPrimaryKey(1);
        users.add(user);
        return users;
    }

    @Override
    public int register(UserExtend userExtend) {
        UserExtend userResult = userExtendMapper.selectUserByUsername(userExtend.getUsername());
        int result;
        if (userResult != null) {
            throw new CustomerException("用户名重复，请重试！");
        } else{
            encryptPassword(userExtend);
            userExtend.setRegisterdate(new Date().getTime());
            userExtend.setEnabled(true);

            result = userExtendMapper.insertUserExtend(userExtend);
            setUserRoles(userExtend, "user");
        }
        return result;
    }

    @Override
    public UserExtend findUserByUserName(String username) {
        UserExtend userResult = userExtendMapper.selectUserByUsername(username);
        if (userResult != null) {
            throw new CustomerException("用户名重复，请重试！");
        } else {
            return userResult;
        }
    }

    private void setUserRoles(UserExtend userExtend, String... roles) {
        for (int i = 0; i < roles.length; i++) {
            String username = userExtend.getUsername();
            userExtendMapper.insertUserRole(username, roles[i]);
        }
    }

    private void encryptPassword(UserExtend userExtend){
        String password = userExtend.getPassword();
        System.out.println("编码前：" + userExtend.getPassword());
        password = new BCryptPasswordEncoder().encode(password);
        userExtend.setPassword(password);
        System.out.println("编码后：" + userExtend.getPassword());
    }
}
