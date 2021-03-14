package com.charlie.college_bbs.service.impl;

import com.charlie.college_bbs.bean.Role;
import com.charlie.college_bbs.dao.RoleMapper;
import com.charlie.college_bbs.service.IRoleService;

import javax.annotation.Resource;
import java.util.List;

public class RoleServiceImpl implements IRoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole() {
        return null;
    }
}
