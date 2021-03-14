package com.charlie.college_bbs.bean.extend;

import com.charlie.college_bbs.bean.Role;
import com.charlie.college_bbs.bean.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserExtend extends User implements UserDetails {

    //用户的角色列表
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    //用户的所有权限
    //private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    /**
     * 该方法用来获取当前用户所具有的角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 直接从roles中获取当前用户所具有的角色，
        // 构造SimpleGrantedAuthority然后返回即可
        if(roles != null) {
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
