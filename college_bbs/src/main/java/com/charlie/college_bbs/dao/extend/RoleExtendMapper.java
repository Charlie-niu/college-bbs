package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.Role;

public interface RoleExtendMapper {

    public Role selectByRoleName(String roleName);
}
