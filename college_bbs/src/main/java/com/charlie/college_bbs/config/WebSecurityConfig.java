package com.charlie.college_bbs.config;

import com.charlie.college_bbs.bean.extend.UserExtend;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import com.charlie.college_bbs.web.filter.security.MyUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Configuration            // 声明为配置类
@EnableWebSecurity()      // 启用 Spring Security web 安全的功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    //ObjectMapper：jackson框架的工具类，用于转换对象为json字符串
    @Bean
    public ObjectMapper ObjectMapperBuilder() {return new ObjectMapper(); }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    RoleHierarchy roleHierarchy () {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_COLLEGE " +
                            "ROLE_COLLEGE > ROLE_TEACHER " +
                            "ROLE_TEACHER > ROLE_STUDENT " +
                            "ROLE_STUDENT > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
        /**
         * 配置认证管理器
         * 1）认证信息（账户名、密码、当前用户权限0）
         */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    /**
     * spring过滤器链配置
     * 1） 需要拦截什么资源
     * 2）什么资源什么角色权限
     * 3）定义认证方式：HttpBasic、FormLogin
     * 4）定义登录页面，定义登录请求地址，定义错误处理方式
     *
     *      .fullyAuthenticated()   // 访问方式：所有资源都要认证才能访问
     *      .httpBasic()                    // 认证方式1
     *      .formLogin().loginPage("/login")// 认证方式2，表单验证,自定义表单
     *      .permitAll()       // 允许所有请求通过
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                //处理跨域请求中的Preflight请求
                //.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()   //放过 option 请求
                    .antMatchers("/user/login").permitAll()
                    .antMatchers("/user/register").permitAll()
                    .antMatchers("/**/find**").permitAll()
//                    .antMatchers("/**/findAll").permitAll()
//                    .antMatchers("/**/findTags").permitAll()
                    .antMatchers("/**/addBrowsenumber").permitAll()
//                    .antMatchers("/**/user/admin/**").hasRole("user")
//                    .antMatchers("/**/user/college/**").hasRole("user")
//                    .antMatchers("/**/user/student/**").hasRole("user")
                    .antMatchers("/**").hasRole("user")
                    .antMatchers("/**").fullyAuthenticated()
                    .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
                    .and()
                    .formLogin()
//                        .loginPage("/user/dologin")
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                               log.info("failureHandler被调用，登录失败");

                                httpServletResponse.setContentType("text/json;charset=utf-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                Message message = MessageUtil.error("登录失败！");
                                if (e instanceof LockedException){
                                    message.setMessage("账户被锁定，请联系管理员");
                                } else if (e instanceof CredentialsExpiredException) {
                                    message.setMessage("账户密码已过期，请联系管理员");
                                } else if(e instanceof AccountExpiredException){
                                    message.setMessage("账户已过期，请联系管理员");
                                } else if(e instanceof DisabledException){
                                    message.setMessage("账户被禁用，请联系管理员");
                                } else if(e instanceof BadCredentialsException){
                                    message.setMessage("用户名或密码输入错误");
                                }

                                String json = ObjectMapperBuilder().writeValueAsString(message);
                                out.write(json);
                                out.flush();
                                out.close();
                            })
                        .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                                log.info("successHandler被调用，登录成功");

                                httpServletResponse.setContentType("text/json;charset=utf-8");

                                PrintWriter out = httpServletResponse.getWriter();
                                UserExtend userExtend = (UserExtend) authentication.getPrincipal();
                                Message message = MessageUtil.success("登录成功！", userExtend);
                                String json = ObjectMapperBuilder().writeValueAsString(message);
                                out.write(json);
                                out.flush();
                                out.close();
                            })
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
                    .clearAuthentication(true)      //表示是否清除身份认证信息
                    .invalidateHttpSession(true)    //表示是否session失效，默认为true
                    .deleteCookies("JSESSIONID")
//                    .addLogoutHandler(new LogoutHandler() {
//                        @Override
//                        public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
//
//                        }
//                    })
                    .logoutSuccessHandler(new LogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                                    HttpServletResponse httpServletResponse,
                                                    Authentication authentication) throws IOException, ServletException {

                            log.info("onLogoutSuccess被调用，注销成功");
                            httpServletResponse.setContentType("text/json;charset=utf-8");

                            PrintWriter out = httpServletResponse.getWriter();
                            Message message = MessageUtil.success("注销成功！");
                            String json = ObjectMapperBuilder().writeValueAsString(message);
                            out.write(json);
                            out.flush();
                            out.close();


                        }
                    })
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint((httpServletRequest, httpServletResponse, e)->{
                                httpServletResponse.setContentType("text/json;charset=utf-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                Message message = MessageUtil.error("尚未登录！请先登录");
                                String json = ObjectMapperBuilder().writeValueAsString(message);
                                out.write(json);
                                out.flush();
                                out.close();
                            });
//                .sessionManagement()            // 定制我们自己的 session 策略
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session

    }

}