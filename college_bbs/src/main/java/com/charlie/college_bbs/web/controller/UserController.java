package com.charlie.college_bbs.web.controller;

import com.charlie.college_bbs.bean.User;
import com.charlie.college_bbs.bean.extend.UserExtend;
import com.charlie.college_bbs.service.IUserService;
import com.charlie.college_bbs.utils.Message;
import com.charlie.college_bbs.utils.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //首页需要查询所有文章（不包括文章的栏目、评论）
    @ApiOperation(value = "查询所有用户")
    @GetMapping("findAll")
    public Message findAll(){
        List<User> users = userService.findAll();
        return MessageUtil.success(users);
    }

//    @ApiOperation(value = "登录")
//    @PostMapping("login")
//    public Message login(String username, String password){
//        System.out.println("请求访问到Controller方法，username :" + username + ",password :" + password);
//        UserExtend userExtend = (UserExtend) userService.(username);
//        return MessageUtil.success(userExtend);
//    }

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public Message register(UserExtend userExtend){
        System.out.println("请求访问到Controller方法，username :" + userExtend.getUsername()
                            + ",password: " + userExtend.getPassword()
                            + ", hobby: " + userExtend.getHobby()
                            + ", gender:" + userExtend.getGender()
                            + ", AboutMe: " + userExtend.getAboutme()
                            + ", CollegeName: " + userExtend.getCollegename()
                            + ", CollegeID: " + userExtend.getCollegeid());
        int result = userService.register(userExtend);
        return MessageUtil.success(result);
    }
}
