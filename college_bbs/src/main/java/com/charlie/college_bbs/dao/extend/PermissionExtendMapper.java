package com.charlie.college_bbs.dao.extend;

import com.charlie.college_bbs.bean.Permission;
import com.charlie.college_bbs.dao.PermissionMapper;

import java.util.List;

public interface PermissionExtendMapper extends PermissionMapper {

    public List<Permission> selectPermissionByUsername(String username);

}
