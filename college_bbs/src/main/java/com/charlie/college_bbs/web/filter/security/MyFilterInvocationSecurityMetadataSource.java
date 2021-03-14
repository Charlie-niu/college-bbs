package com.charlie.college_bbs.web.filter.security;

import com.charlie.college_bbs.bean.Role;
import com.charlie.college_bbs.bean.extend.MenuExtend;
import com.charlie.college_bbs.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 1.一开始注入了MenuService，
 *  MenuService的作用是用来查询数据库中url pattern和role的对应关系，
 *  查询结果是一个List集合，集合中是Menu类，
 *  Menu类有两个核心属性，一个是url pattern，即匹配规则(比如/admin/**)，
 *  还有一个是List<Role>,即这种规则的路径需要哪些角色才能访问。
 *
 * 2.我们可以从getAttributes(Object o)方法的参数o中提取出当前的请求url，
 *  然后将这个请求url和数据库中查询出来的所有url pattern一一对照，看符合哪一个url pattern，
 *  然后就获取到该url pattern所对应的角色，当然这个角色可能有多个，所以遍历角色，
 *  最后利用SecurityConfig.createList方法来创建一个角色集合。
 *
 * 3.如果getAttributes(Object o)方法返回null的话，
 *  意味着当前这个请求不需要任何角色就能访问，甚至不需要登录。
 *
 * 4. getAttributes(Object o)方法返回的集合最终会来到AccessDecisionManager类中
 *
 *
 */
//@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuServiceImpl menuServiceImpl;//MenuService的作用是用来查询数据库中url pattern和role的对应关系
    AntPathMatcher antPathMatcher = new AntPathMatcher(); //主要用来做类URL字符串匹配

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if ("/login".equals(requestUrl) || "/index".equals(requestUrl)) {
            //任何角色都能访问 登录请求和访问首页请求
            return null;
        }

        // 这步操作中，涉及到一个优先级问题，
        // 比如我的地址是/employee/basic/hello,
        // 这个地址既能被/employee/**匹配，也能被/employee/basic/**匹配，
        // 这就要求我们从数据库查询的时候对数据进行排序，
        // 将/employee/basic/**类型的url pattern放在集合的前面去比较。

        List<MenuExtend> allMenu = menuServiceImpl.getAllMenu();
        for (MenuExtend menuExtend : allMenu) {

            // 遍历数据库中所有的Menu的URL，
            // 如果有URL和当前请求的URL一致，且这个Menu能被访问
            // 那么获取这个Menu下的所有角色名称，然后返回给SecurityConfig
            if (antPathMatcher.match(menuExtend.getUrl(), requestUrl)
                    && menuExtend.getRoles().size() > 0) {
                List<Role> roles = menuExtend.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
