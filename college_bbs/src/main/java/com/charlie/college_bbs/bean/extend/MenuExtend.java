package com.charlie.college_bbs.bean.extend;

import com.charlie.college_bbs.bean.Menu;
import com.charlie.college_bbs.bean.Role;

import java.util.List;

public class MenuExtend extends Menu {

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
