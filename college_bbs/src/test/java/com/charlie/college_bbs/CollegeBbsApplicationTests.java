package com.charlie.college_bbs;

import com.charlie.college_bbs.bean.Permission;
import com.charlie.college_bbs.bean.User;
import com.charlie.college_bbs.dao.extend.PermissionExtendMapper;
import com.charlie.college_bbs.dao.extend.UserExtendMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CollegeBbsApplicationTests {

    @Resource
    private UserExtendMapper userExtendMapper;
    @Resource
    private PermissionExtendMapper permissionExtendMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void TestSelectUserByUsername(){
        User user = userExtendMapper.selectUserByUsername("Charlie");
        System.out.println(user);
    }


    @Test
    public void TestUserPermission(){
        List<Permission> permissions = permissionExtendMapper.selectPermissionByUsername("Charlie");
        System.out.println(permissions);
    }

}
