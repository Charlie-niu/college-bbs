package com.charlie.college_bbs.web.filter.security;

import com.charlie.college_bbs.bean.extend.UserExtend;
import com.charlie.college_bbs.dao.extend.UserExtendMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserExtendMapper userExtendMapper;

    /**
     * 这个方法将根据用户名去查找用户，
     * 如果用户不存在，则抛出UsernameNotFoundExceptio
     * n异常，
     * 否则直接将查到的UserExtend返回。
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserExtend userExtend = userExtendMapper.selectUserByUsername(username);
        System.out.println("loadUserByUsername方法被调用，userExtend: " + userExtend);
        if (userExtend == null) {
            throw new UsernameNotFoundException("用户名不对");
        }

//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(userExtend.getRoles());
//        return new User(userEntity.getUsername(), userEntity.getPassword(), simpleGrantedAuthorities);
        userExtend.setRoles(userExtendMapper.getUserRolesByUserID(userExtend.getId()));
        return userExtend;
    }


    /**
     * 权限字符串转化
     *
     * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") + SimpleGrantedAuthority("ADMIN")
     *
     * @param roleStr 权限字符串
     */
//    private List<SimpleGrantedAuthority> createAuthorities(String roleStr){
//        String[] roles = roleStr.split(",");
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        for (String role : roles) {
//            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
//        }
//        return simpleGrantedAuthorities;
//    }
}
