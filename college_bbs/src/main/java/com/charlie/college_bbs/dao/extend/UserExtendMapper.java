package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.Role;
import com.charlie.college_bbs.bean.extend.UserExtend;

import java.util.List;

public interface UserExtendMapper {

    public UserExtend selectUserByUsername(String username);

    public int insertUserExtend(UserExtend userExtend);

    public void insertUserRole(String username, String rolename);

    public List<Role> getUserRolesByUserID(Integer id);
}
