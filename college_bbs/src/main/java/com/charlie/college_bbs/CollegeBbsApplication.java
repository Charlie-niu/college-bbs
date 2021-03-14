package com.charlie.college_bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication()//(exclude = {SecurityAutoConfiguration.class})//参数用来关闭SpringSecurity
//@MapperScan(basePackages = { "com.charlie.college_bbs.dao" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class CollegeBbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeBbsApplication.class, args);
    }

}
